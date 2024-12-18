# Hotel Bill Computation

## DESCRIPTION
The Hotel Bill Computation program, HotelBilly, is designed to calculate the total bill for a hotel stay based on the type of room, number of nights stayed, number of guests, and other relevant details. It also collects basic user information for record-keeping purposes.

## USAGE

### Installation

#### Pre-requisites
To use this program, ensure you have any version of supported Java installed on your system. Compile the Java program using a compiler like javac and run the compiled class file.

You can install java from [Adoptimum](https://adoptium.net/installation/). 

#### Installing and Executing HotelBilly

**WINDOWS**

1. Open your browser and type https://github.com/jeiya1/HotelBilly.

2. Left-Click on  the `<> Code` button and download the program as a zip file to your desired directory.

![github green code button](images/github_download_1.png)

![github download as zip](images/github_download_2.png)

3. Open your downloads directory (or folder where the zip file is located in), and then extract the zip file.

Right-click the zip file and then click on `Extract All..`.

![extract zipe file](images/unzip_1.png)

Left-click the `Extract` button.

![extract zipe file](images/unzip_2.png)

Enter the extracted directory and then enter the src directory.

![hotel dir](images/hotel_dir_1.png)

![hotel dir](images/hotel_dir_2.png)

![hotel dir](images/hotel_dir_3.png)

In the Terminal app, type this:

```powershell
java HotelBilly.java
```

4. Enjoy!

**LINUX**

1. Download Java and git.

```bash
sudo apt update && \
sudo apt install -y openjdk-17-jdk git
```

2. Clone the repository.

```bash
git clone https://github.com/jeiya1/HotelBilly.git && \
cd HotelBilly/src
```

3. Run the java program

```bash
java HotelBilly.java
```

## CODE STRCUTURE

### Modules

- Main Class: HotelBilly

    - Handles user input

    - Comutes the total bill

    - Appplies discount and additional charges

### Program Flow

## EXAMPLES

### Basic Example

- Room Type: Standard

- Occupancy Size: Single

- Number of Nights: 2

- Number of Guests: 1

Output: Total Bill = (1800 * 2) + 12% VAT

### Advanced Example

- Room Type: Suite

- Occupancy Size: Double

- Number of Nights: 5

- Number of Guests: 8

Output: Total Bill = (1800 * 5) + (10% * 8 * 4000) - 15% discount + 12% VAT

## ERROR HANDLING

- INCORRECT INPUT: If the user inputs invalid data (e.g., text where a number is expected), the program prompts the user to re-enter the information.

- GUEST LIMIT EXCEEDED: If the number of guests exceeds the room's capacity, the program notifies the user and requests correct input.

- MISSING DATA: If the values needed to get the total bill computation is incomplete.

## LIMITATIONS AND KNOWN ISSUES

## CONTRIBUTORS

- lourbirds

- hizmoonlover

- mreatsaf