# Colour Of The Month

**Credit:** All colour names and colour codes were taken from [HTML COLOR CODES](https://htmlcolorcodes.com/color-names/) on 27/05/2024.

This is a really simple Android app that displays a different colour each month.

The app is automatically kept up to date by a GitHub Actions workflow that runs on the first day of each month.

I'm not going to lie, I created this app to prevent my Android developer account from getting de-activated 🙂

## TODOs for 1.0

* Long press when showing expanded box should show changelog
* Change log should be:
  * Wide enough to show a full expanded + padding - Up to 80% width of screen. Min 70% width. Height automatic but test it with a few months to make sure auto scrolls. Stop at 90% height
  * Should be contain a card for each month in history
  * This card should be surrounded by the colour of that month?
  * Card should be tappable to expand and show the same expanded information
  * Long press will close changelog
* Implement uploading to Play Store as per job 2 on <https://medium.com/@vontonnie/automating-success-github-actions-workflow-for-android-app-deployment-908095d53b97>
