[![](https://jitpack.io/v/BharathKSunil/AndroidUtils.svg)](https://jitpack.io/#BharathKSunil/AndroidUtils)
# AndroidUtils
A Simple Collection of Android Utilities that I have been using in my projects. The library is in pre-release stage and requires more efforts and proper documentation and structuring.

## Use in your project
Add it in your root `build.gradle` at the end of repositories:
```gradle
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency to your module:
```gradle
dependencies {
	        implementation 'com.github.BharathKSunil:AndroidUtils:v1.0.1-beta'
	}
```

## Some Utils
- `CircleTransform`: A Util to transform an image to circleimage when using `Picasso` library.
- `DateUtil`: A Utility for maintaining uniformity of date & time strings.
- `FragmentTransactionUtils`: Use this Util easily to load fragments with entry animations.
- `SnackBarUtils`: This Util has methods related to snackBars, creating appTheme specific SnackBar, error SnackBar.
- `TextDrawable`: This Util provides images with letter/text like the Gmail app(from `com.amulyakhare:com.amulyakhare.textdrawable`)
- `ValidationUtils`: This Util provides all type of field verifications like email, phone, or password strengths.
- `ViewUtils`: This Util provides view related operations like visibility, enabled/disabled alerts etc.

## NOTE
This library is in a very primitive stage and Utils are not structured properly, many other utilities will be added soon.
