# SimformPractical
Brijesh's Simform Practical Assignment


Android Test Paper
Description:
Create an app that displays List of Users. Every time, app opens it should show the old
data from previous session, meanwhile it should fetch new users list in background, then
update the current list with new one. The app should be usable even if app is not
connected to the internet.
Url: https://randomuser.me/api/?results=100
Strategy:
• If internet is available:
o Display list from local
o Fetch list of Users from remote
o Replace list in local with the new list from remote
• If no internet is available:
o Display list from local
• Design the screen for user details with collapsing image of user.
• Write the unit test case for API call.
Duration: 2 hours
Bonus points:
• MVVM / MVP / MVI architecture
• AndroidX
• Kotlin coroutine / Rx / RxAndroid
• LiveData and ViewModel
• Any dependency injection component
• Room
• Retrofit
• Glide
