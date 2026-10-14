# ProfitScan UK Android
Native Android WebView shell for the existing ProfitScan UK single-page app. Includes camera permission and external marketplace links.

## APK
GitHub repository > Actions > Build ProfitScan Android APK > latest successful run > Artifacts > ProfitScan-UK-Android-APK. Download ZIP, extract app-debug.apk, and install on Android after reviewing Android's install prompts.

The build creates a **debug-signed testing APK**, not a Play Store release. The app requires internet for fresh product data. WebView local storage is separate from Chrome/PWA storage; **existing inventory and bookkeeping will not automatically migrate**. Export backups before switching. The app does not automatically publish listings or provide live marketplace price APIs.
