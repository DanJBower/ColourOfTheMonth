# Colour Of The Month

**Credit:** All colour names and colour codes were taken from [HTML COLOR CODES](https://htmlcolorcodes.com/color-names/) on 27/05/2024.

This is a really simple Android app that displays a different colour each month.

The app is automatically kept up to date by a GitHub Actions workflow that runs on the first day of each month.

I'm not going to lie, I created this app to prevent my Android developer account from getting de-activated 🙂

## TODOs

* Create the app using Kotlin and JetPack Compose
* Make it only keep pipeline artifacts for 32 days if possible - in terms of the apk as they can be big
* Background should be colour of the month
* Foreground should be a box with text in
* Initially should contain just the name of the colour and current month
* Box should have shadow to make it stand out
* Tapping anywhere on the app screen should expand the box over a second or something to show additional information:
  * HEX value
  * RGB value
  * HSL value
  * HSV value
* Text should fade in and out as the box expands and contracts
* Clip text to box
* If this animation too complicated, just fade in and out everything
* Further taps toggle between the two boxes
* Long press when showing expanded box should show changelog
* Icon, should be a paint palette with several bits of paint on of the current colour of the month. Use SVG to make this easy to update
* Change log should be:
  * Wide enough to show a full expanded + padding - Up to 80% width of screen. Min 70% width. Height automatic but test it with a few months to make sure auto scrolls. Stop at 90% height
  * Should be contain a card for each month in history
  * This card should be surrounded by the colour of that month?
  * Card should be tappable to expand and show the same expanded information
  * Long press will close changelog
