# STextFilter
Simple & Light weighted dependency that deals with texts for filtering and cleaning for messages & etc...

# Implementation
> **Step 1.** Add the JitPack repository to your build file
> Add it in your root build.gradle at the end of repositories:
```gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

> **Step 2.** Add the dependency:
```gradle
	dependencies {
	        implementation 'com.github.smith8h:STextFilter:1.0.0'
	}
```

# Documentation
• Remove text using RegEx (remove by pattern)
```java
    // returns string
    STextFilter.removeWithRegEx("pattern", "text");
```
• check if text is is e-mail
```java
    // returns boolean
    STextFilter.isEmail("text");
```
• keep Letters only
```java
    // returns string
    STextFilter.keepLetters("text");
```
• keep Sentences only
```java
    // returns string
    STextFilter.keepSentences("text");
```
• keep Numbers only
```java
    // returns string
    STextFilter.keepNumbers("text");
```
• keep Symbols only
```java
    // returns string
    STextFilter.keepSymbols("text");
```
• removevSpaces
```java
    // returns string
    STextFilter.removeSpaces("text");
```

**Create a new instance** for some methods:
```java
    STextFilter filter = new STextFilter(this);
```
• to cencor (block) bad banned words, The library already has its data of banned words.
```java
    String text = "Dummy text contains bad words";
    String newText = filter.censorBannedWords(text);
```

To add more banned words to library data, follow these steps:
- 1. create a txt file (with any recognizable name)
- 2. list all the banned words, in an order **word under word** like:
 - > As----ole
 - > Bad----s
- 3. add it to `res/raw` or `assets` folders in your project
- 4. use these methods each for its respective directory:
```java
    // add from raw resources
    filter.addDataFromRawResource(R.raw.file_name);
    
    // add from assets
    filter.addDataFromAssets("file_name.txt");
```

• to check is text contains Email
```java
    String text = "Dummy text...";
    // if statement (to do once)
    // while loop to find more than 1 email
    while (filter.containsEmail(text)) {
        // use filter.start() to get found email index
        // use filter.end() to get found email last index
    }
```
• to check is text contains specific chars|text|words|etc...
```java
    String text = "Dummy text...";
    // if statement (to do once)
    // while loop to find more than 1 email
    while (filter.contains("to be found", text)) {
        // use filter.start() to get found index
        // use filter.end() to get found last index
    }
```


