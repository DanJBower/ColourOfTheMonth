# Colour Of The Month

**Credit:** All colour names and colour codes were taken from [HTML COLOR CODES](https://htmlcolorcodes.com/color-names/) on 27/05/2024.

This is a really simple Android app that displays a different colour each month.

The app is automatically kept up to date by a GitHub Actions workflow that runs on the first day of each month.

I'm not going to lie, I created this app to prevent my Android developer account from getting de-activated 🙂

## TODOs for 1.0

* Change log should be:
  * **TODO:** Stop at 90% height
* Implement uploading to Play Store as per job 2 on <https://medium.com/@vontonnie/automating-success-github-actions-workflow-for-android-app-deployment-908095d53b97>
  * releaseFile - aab
  * serviceAccountJsonPlainText - secret
  * packageName - just copy it
  * track - production
  * inAppUpdatePriority - 5
  * userFraction - 1.0
  * status - completed
  * whatsNewDirectory - Work this out
  * mappingFile - look into how to set this up
  * nativeDebugSymbols - look into if this is needed
