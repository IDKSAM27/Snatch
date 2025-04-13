# Snatch

Snatch is a lightweight app that captures your handwritten notes and instantly saves them as organized PDFs on your device.


## File Structure
```graphql
Snatch/
├── app/
│   ├── java/com/yourname/snatch/
|   |   |── ui
|   |   |   └── Color.kt
|   |   |   └── Theme.kt
|   |   |   └── Type.kt
│   │   ├── activities/
│   │   │   └── MainActivity.java
│   │   │   └── PreviewActivity.java         ← Preview captured image
│   │   ├── utils/
│   │   │   └── PdfUtils.java                ← PDF creation, image compression, etc.
│   │   ├── camera/
│   │   │   └── CameraHelper.java            ← Camera code abstraction
│   │   ├── storage/
│   │   │   └── FileManager.java             ← Save image, create folders, save PDFs
│   │   └── SnatchApp.java                   ← Application class
│   └── res/
│       ├── layout/
│       │   └── activity_main.xml
│       │   └── activity_preview.xml         ← Showing image before saving
│       ├── drawable/                        ← App icons, custom UI graphics
│       ├── values/
│       │   └── strings.xml
│       │   └── colors.xml
│       │   └── themes.xml
│       └── mipmap/                          ← App launcher icons
└── AndroidManifest.xml
```
### Purpose 


|Folder         |Purpose                                                      |
|-------------- | ------------------------------------------------------------|
|`activities/`    |Holds your screens (Main screen, preview screen, etc.)  |
|`utils/`	        |General utilities (like PDF creation, timestamping)    |
|`camera/`	    |All code related to camera capture                     |
|`storage/`	    |Saving to file system, generating filenames/folders       |
|`layout/`	    |UI XML files                                   |
|`drawable/`	    |Image resources|


