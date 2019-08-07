# Sales Tax Problem
***
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products which are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.

When I purchase items I receive a receipt that lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.

Write an application that prints out the receipt details for these inputs:
***
### INPUT:

*Input 1*:
>1 book at 12.49  
>1 music CD at 14.99  
>1 chocolate bar at 0.85  

*Input 2*:
>1 imported box of chocolates at 10.00  
>1 imported bottle of perfume at 47.50  

*Input 3*:
>1 imported bottle of perfume at 27.99  
>1 bottle of perfume at 18.99  
>1 packet of headache pills at 9.75  
>1 box of imported chocolates at 11.25  

### OUTPUT

*Output 1*:
>1 book : 12.49  
>1 music CD: 16.49  
>1 chocolate bar: 0.85  
>Sales Taxes: 1.50  
>Total: 29.83  

*Output 2*:
>1 imported box of chocolates: 10.50  
>1 imported bottle of perfume: 54.65  
>Sales Taxes: 7.65  
>Total: 65.15  

*Output 3*:
>1 imported bottle of perfume: 32.19  
>1 bottle of perfume: 20.89  
>1 packet of headache pills: 9.75  
>1 imported box of chocolates: 11.85  
>Sales Taxes: 6.70  
>Total: 74.68  
  
***
The System assumes that once the items are purchased, the generated receipt in text file is provided for processing and formatting
ShoppingApplication is main execution class
*Note*: The text file must be placed in the C:\Users\Public\inputs folder. Sample files from assignment PDF are kept under src/main/resources
 
 
### Assumptions
1. The input text file follows the following syntax:
    <pre>
      1 book at 12.49
      1 imported box of chocolates at 10.00
    </pre>
    This is in form of "<quantity> <product> at <price>"
2. Product Quantity is integer and negative value will throw Invalid Quantity Exception 
3. Price is a decimal value and negative value will throw Invalid Amount Exception
4. Imported items will have the word 'import' in them.
5. The input is case sensitive and will be in lower case, In the off case that the input is upper case due to the input application sending that way, then we will process the product but it will be as below:
 BOX OF IMPORTED CHOCOLATES will be considered as OTHER and NOT FOOD
 BOX OF IMPORTED CHOCOLATES will be considered as DOMESTIC and not IMPORTED
 
6. 2 book at 12.49 will mean that *2 books are marked for sale at 12.49 each*  

### Processing Logic

> 1 book at 12.49  

For each of the input text files present in given folder, the receipt generation is invoked.
For a input file, each line is read and matched with regex, the regex returns Quantity, Product and its Price.
If any of the input is not consistent with expected format, the application will skip this input file and throw relevant exception and log it. It will
 continue with processing of the rest of the input files

The following parameters are stored in Product Value Object by ProductBuilder
> name: string  
> quantity: integer  
> basePrice: decimal  

The evaluation of product Category [FOOD|MEDICINE|BOOK|OTHER] is matched on name if it contains as follows:
chocolate: FOOD
pill: MEDICINE
book: BOOK
other cases: OTHER

The evaluation of product Origin Type is  [IMPORTED|DOMESTIC] is matched on name if it contains as follows:
imported: IMPORTED
other cases: DOMESTIC


The Product will have Net Price and Sales tax individually calculated based on its base price, the Category and Imported Type
All the items that have been added to Cart will be iterated and will format the receipt output

Alternate Design ideas
#Could have used TAX RATE from props file => System is static with limited scope and Category like ENTERTAINMENT or ORIGIN from other states could be added in future and calls for further development. 
#Could have used different product subclass e.g. FoodProduct, but have only tax rate difference => not meaningful

Effort Tracker for assignment(man days): 3 days

If I had more time, I could have deisgned the application that it will return output in format as given in 3rd output example.


testPrintReceiptDetailsInputCase3 will fail because the outputs are not matching.
1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 imported box of chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68

1 imported bottle of perfume: 32.19
1 bottle of perfume: 20.89
1 packet of headache pills: 9.75
1 box of imported chocolates: 11.85
Sales Taxes: 6.70
Total: 74.68


jenkins
docstring
junit
class diagram

build
Release document# basic-sales
