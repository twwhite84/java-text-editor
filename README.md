# Text Editor

## Members
23012962 - Jamin Stratford (Submitting Code Files)

16205672 - Tom White (Submitting README)

## Most Significant Commits
### Jamin Stratford
Implement an "Action" system to easily modularise code
- 3307a8dd2fc3f0a2433f30e204f05b377512adce

Implementing converting text to PDF
- b269105c685396e6c98a6663389b43208f7156a3

Detecting text content type and setting syntax highlighting
- 639523e1882212c37ffbe23873ac9b3c9ca9832c
### Tom White
Implementing Search Functionality:
- 25d9d934292064e65e27969b6c4ff2f7e105f1b1 - Counting num of matches
- cc5c6711a91e1750f6a9406f08cdd4b245d0e9f0 - Selecting matches

Implementing Printing Function:
- ef707dae466962d79410c9b2a85f09eae39f4de0

Implementing Saving on Exit:
- a4b2a5fd1e4ba7f5962be963b313624f2778ef18 - Add modal with options
- 84f9c54cdf7379e3e04e772ac85209f32ae247e1 - Implement functionality to each option

## Running the Program
To run the program, first clone the repository to your computer and enter into the directory.

Run the following commands within the project directory
```bash
mvn clean compile exec:java
```

The program uses a `config.yml` file to provide some options. The default used when running this command is found in the base directory. It is copied to the target folder when running this command to be used. You can provide your own `config.yml` by updating the config in the base directory or including it with the built .jar file (see below)
### Building Application
To build the program into a runnable .jar file, run the following command
```bash
mvn clean package
```

This will output the .jar file to `/target/TextEditor.jar`. You can then run this jar via double-clicking or running the following command in the same directory as it.
```bash
java -jar TextEditor.jar
```

Note that the program comes with a `config.yml` file that must be in the same directory as the .jar file
### Running Tests
To run all tests for the program run the following command
```bash
mvn clean test
```

## Features
Below is a list of the more complicated features and how they are implemented (to reduce confusion while marking/testing)

### Select Text, Copy, Paste, and Cut
This functionality firstly works with the normal key binds:
- Selecting text my holding mouse down
- Copy by Ctrl + C
- Paste by Ctrl + V
- Cut by Ctrl + X

It can also be accessed by right-clicking text. It will bring up a menu that you can select to perform these operations.

### PDF Conversion
To convert the text file to PDF, you can save as PDF. Use the Save As function via the File menu or press Ctrl + Shift + S. Select the file location and set the file type to PDF. The file at this saved location will be the PDF version of the text.
