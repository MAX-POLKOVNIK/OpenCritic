# OpenCritic
[OpenCritic](https://opencritic.com/) unofficial mobile app

Access your favorite site with mobile app

# Features/Screen List

- Main screen
- Game Review screen
- Author's Reviews screen
- Outlet's Reviews screen
- Game Browser screen
- Calendar screen
- Your Lists screen
- Your List (Wanted/Played/Favorite/Custom) screen
- Sign In screen (in some way...)
- About screen
- News screen
- Article screen

# Android

<img src="https://github.com/MAX-POLKOVNIK/OpenCritic/assets/8066100/ec838d93-ae4c-433b-b979-23adcf7bd30c" width="200" />
<img src="https://github.com/MAX-POLKOVNIK/OpenCritic/assets/8066100/f6e5f4f9-ac99-4103-8442-ee4d544abd1d" width="200" />
<img src="https://github.com/MAX-POLKOVNIK/OpenCritic/assets/8066100/85fd3e47-407e-469e-af66-272b874e47a6" width="200" />

# iOS


<img src="https://github.com/MAX-POLKOVNIK/OpenCritic/assets/8066100/abd6e7aa-e202-4138-8516-37a043269f9f" width="200" />
<img src="https://github.com/MAX-POLKOVNIK/OpenCritic/assets/8066100/304c373d-20d7-4650-8d31-367890be6892" width="200" />
<img src="https://github.com/MAX-POLKOVNIK/OpenCritic/assets/8066100/b0d46b0b-87e5-45cf-a33b-4a6be819add6" width="200" />

# How to authorize and work with game lists
This app is not official opencritic app, so we can't use simple auth like this done on website.
Embedded auth forbidden by almost all companies. But we can do it in another way: 

- Go and authorize yourself on opencritic.com
- After you got authorized go to network tab of browser
- Refresh opencritic.com page
- Find in network tab request `GET https://api.opencritic.com/api/profile`
- Copy header with named `x-access-token`
- Copy and paste it in app auth screen
- That's all. You can see your lists, add/remove to/from Want/Played/Favorite list

# Tools
- Android Studio
- KMP plugin
- Xcode (for iOS)

# Note
This app is not affiliated with OpenCritic in any way.
Written for fun.
