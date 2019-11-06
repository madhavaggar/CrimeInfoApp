# CrimeInfoApp
An Application to give information about UK Crimes.
The app organises UK Crime Data. The app makes use of the data available on the UK Police website : https://data.police.uk/ and makes API calls using Retrofit to fetch the data.
#APP Features
Search for crime records by entering approximate Latitude, Longitude and the month for which you are looking the data for. Recycler List displays result based on input.
To make the app more user-friendly there is a feature to add a crime to favourites by swiping right any crime and removing from favourites by swiping left. An appropriate toast message is displayed .
The crime record activity contains all the data available on the server along with a Google Map icon, which when clicked launches the map location of the place where the crime is committed. This is done by using a Google Map API.
To make the app more user-friendly, there is a search option given in the "Favourite Crimes" and "Police Force" activity.
Fragments are implemented in the primary activities which show the purpose of the app and data source.
