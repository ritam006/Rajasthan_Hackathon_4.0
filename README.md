# Rajasthan-Hackathon-4.0
Mobile application: "Jal"
![water-wave-icon-abstract-logo-vector-5241866 1](https://user-images.githubusercontent.com/25881743/37683526-e6e6bf0e-2cb2-11e8-9b92-6495022842d7.jpeg)

## Overview
We are proposing an end-to-end water management solution for the Rajasthan government that will ensure proper usage of water.
It includes an android application for each government body that is in charge of distributing water resources using Machine Learning.
The app would show real time purity levels of the nearby water bodies using IoT devices installed at the sites.
It would notify the people when a water tanker is being sent to their location along with its real-time location on the map.
The government is also sent an alert when a drought is being predicted in the next three months, implemented using an Artificial Neural Network, so that they can take appropriate measures.


## Features
- Drought prediction using an Artificial Neural Network
- Water need prediction, along with the division of the need into clean and regular water, using Machine Learning
- Real-time purity indication of nearby water resources
- Notification of water tankers sent for replenishment 


## How it works

1. The app uses the data collected during surveys for training a neural network that gives us the clean and regular water need of households. The househlolds are represented using the feature vector (occupation_type, infants, kids, adults, senior_citizens), which are used for prediction of the water need using the trained ANN.
2. The government can use that data when they need to send in water supplies for the households. The families get a notification on the app, or as an SMS, whichever possible, when a water tanker has been sent for them.
3. The app uses an ANN trained on 105 years worth of Rajasthan's weather data, that predicts the possibility of a drought in the next 3 months and sends an alert to the government officials so that appropriate actions can be taken. It also sends a notification to the user about drought so that he could use water effeciently saving it for future.
4. The goverment will install IoT devices in the water bodies of the area to calculate the purity levels and that data would be displayed to the users on the app in real-time.

## Screenshots

![screenshot from 2018-03-21 04-35-55](https://user-images.githubusercontent.com/25881743/37687880-550e3056-2cc3-11e8-8d08-b7d34b83b1ad.png)

![screenshot from 2018-03-21 04-38-27](https://user-images.githubusercontent.com/25881743/37687885-58113e7e-2cc3-11e8-8e95-8920ffcd116c.png)


## Technology Stack

- Machine Learning
- Backend- Flask Framework
- Android App
