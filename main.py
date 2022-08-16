import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split

def getBitcoinData():
    data = pd.read_csv('historical_crypto.csv') #plug in the csv here
    return data

def getBitcoinPrice():
    #get price of bitcoin over time
    plt.figure(figsize = (12, 7))
    plt.plot(data["time"], data["BTC Close"], color='goldenrod', lw=2)
    plt.title("Bitcoin Price since 2021 9/30", size=20)
    plt.xlabel("Time", size=20)
    plt.ylabel("$ Price", size=20)
    plt.show()

def getBitcoinVolume():
    # show bitcoin volume over time
    plt.figure(figsize = (12, 7))
    plt.plot(data["time"], data["BTC Volume"], color='royalblue', lw=2)
    plt.title("Bitcoin Volume over time", size=25)
    plt.xlabel("Time", size=20)
    plt.ylabel("Volume", size=20)
    plt.show()

#obtain the data for bitcoin
data = getBitcoinData()

#select factors to include in our model
factors = ['BTC Open','BTC High', 'BTC Low','BTC Volume']
output_label = 'BTC Close'

#define x and y variables for us to train and test our model with
xtrain, xtest, ytrain, ytest = train_test_split(data[factors], data[output_label], test_size = 0.3)

#Using Sklearn's linear regression model
model = LinearRegression()

#fit xtrain and ytrain
model.fit(xtrain,ytrain)

#test our model (r_sq is COD which is a statistical measurement that examines how differences in one variable can be explained by the difference in a second variable, when predicting the outcome ofa  given event
r_sq = model.score(xtest,ytest)

#print out some statistics so we can see the regression
print('coefficient of determination:', r_sq)
print('intercept:', model.intercept_)
print('slope:', model.coef_)

#shift the data and use predict to predict the future price from given data
future_price = data.shift(periods = 30).tail(30)
prediction = model.predict(future_price[factors])

#graphing what we have found
plt.figure(figsize = (12, 7))
plt.plot(data["time"][-400:-60], data["BTC Close"][-400:-60], color='black', lw=2)
plt.plot(future_price["time"], prediction, color='red', lw=2)
plt.title("BTC Price Prediction", size=20)
plt.xlabel("Time (2hrs)", size=20)
plt.ylabel("$ Price", size=20)
plt.xticks(np.arange(0,400, 75))
plt.show()





