CREATE DATABASE [5AnhLucDB]

Use [5AnhLucDB]

CREATE TABLE role (
    id INT PRIMARY KEY IDENTITY(1,1),
    role VARCHAR(255) NOT NULL
);

INSERT INTO role (role)
VALUES 
('Customer'),
('Staff'),
('Admin');

CREATE TABLE Account (
    AccountID INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(255) NOT NULL,
    Password NVARCHAR(255) NOT NULL,
	RoleID int,
	FOREIGN KEY (roleID) REFERENCES role(id),
);

CREATE TABLE Admin (
    AdminID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(255),
    PhoneNumber NVARCHAR(15),
    Email NVARCHAR(255),
    AccountID INT FOREIGN KEY REFERENCES Account(AccountID)
);

CREATE TABLE Staff (
    StaffID INT PRIMARY KEY IDENTITY(1,1),
    StaffName NVARCHAR(255),
    PhoneNumber NVARCHAR(15),
    Email NVARCHAR(255),
	Salary int,
	NewAccount bit,
    AccountID INT FOREIGN KEY REFERENCES Account(AccountID)
);

CREATE TABLE Customer (
    CustomerID INT PRIMARY KEY IDENTITY(1,1),
    CustomerName NVARCHAR(255),
    PhoneNumber NVARCHAR(15),
    Email NVARCHAR(255),
    Point int,
	AccountID INT FOREIGN KEY REFERENCES Account(AccountID)
);

CREATE TABLE Voucher (
    VoucherID INT PRIMARY KEY IDENTITY(1,1),
    VoucherName NVARCHAR(255),
    Value int
);

CREATE TABLE Voucher_Customer (
    VoucherID INT FOREIGN KEY REFERENCES Voucher(VoucherID),
    CustomerID INT FOREIGN KEY REFERENCES Customer(CustomerID),
    Voucher_CustomerID INT PRIMARY KEY,
);

CREATE TABLE [Table] (
    TableID INT PRIMARY KEY IDENTITY(1,1),
    TableName NVARCHAR(255),
    Status NVARCHAR(255)
);


CREATE TABLE [Guest] (
    GuestID INT PRIMARY KEY IDENTITY(1,1),
    GuestName NVARCHAR(255),
    GuestPhone NVARCHAR(255)
);

CREATE TABLE [Table_Order] (
    Table_OrderID INT PRIMARY KEY IDENTITY(1,1),
    TotalPrice int,
    OrderDate DATETIME,
	CustomerID INT NULL FOREIGN KEY REFERENCES [Customer](CustomerID),
	GuestID INT NULL FOREIGN KEY REFERENCES [Guest](GuestID),
	TableID INT NULL FOREIGN KEY REFERENCES [Table](TableID),
);

CREATE TABLE [Order] (
    OrderID INT PRIMARY KEY IDENTITY(1,1),
    GuestNote NVARCHAR(255),
    GuestID INT NULL FOREIGN KEY REFERENCES [Guest](GuestID),
	CustomerID INT NULL FOREIGN KEY REFERENCES [Customer](CustomerID),
    Table_OrderID INT NULL FOREIGN KEY REFERENCES [Table_Order](Table_OrderID)
);

CREATE TABLE Bill (
    BillID INT PRIMARY KEY IDENTITY(1,1),
    BillName NVARCHAR(255),
    BillDate DATETIME,
    OrderID INT FOREIGN KEY REFERENCES [Order](OrderID),
    VoucherID INT NULL FOREIGN KEY REFERENCES [Voucher](VoucherID)
);


CREATE TABLE Feedback (
    FeedbackID INT PRIMARY KEY IDENTITY(1,1),
    FeedbackNote NVARCHAR(MAX),
    CustomerID INT FOREIGN KEY REFERENCES Customer(CustomerID)
);

CREATE TABLE Discount (
    DiscountID INT PRIMARY KEY IDENTITY(1,1),
    DiscountName NVARCHAR(255),
    Value int,
	Date Date
);

CREATE TABLE FoodCategory (
	CategoryID INT PRIMARY KEY IDENTITY(1,1),
	CategoryName NVARCHAR(255)
)

CREATE TABLE Food (
    FoodID INT PRIMARY KEY IDENTITY(1,1),
    FoodName NVARCHAR(255),
	FoodPrice int,
	CategoryID INT NULL FOREIGN KEY REFERENCES FoodCategory(CategoryID),
    Status NVARCHAR(255)
);

CREATE TABLE Drink_Category (
    CategoryID INT PRIMARY KEY IDENTITY(1,1),
    CategoryName NVARCHAR(255)
);

CREATE TABLE Brand (
    BrandID INT PRIMARY KEY IDENTITY(1,1),
    BrandName NVARCHAR(255)
);

CREATE TABLE Drink (
    DrinkID INT PRIMARY KEY IDENTITY(1,1),
    DrinkName NVARCHAR(255),
	DrinkPrice INT,
    Quantity INT,
	CategoryID INT NULL FOREIGN KEY REFERENCES Drink_Category(CategoryID),
	BrandID INT NULL FOREIGN KEY REFERENCES Brand(BrandID),
    Status NVARCHAR(255)
);

CREATE TABLE Ingredient (
    IngredientID INT PRIMARY KEY IDENTITY(1,1),
    IngredientName NVARCHAR(255),
    Quantity INT
);

CREATE TABLE Export_Import (
    Export_ImportID INT PRIMARY KEY IDENTITY(1,1),
    Quantity INT,
    Date DATETIME,
    IngredientID INT NULL FOREIGN KEY REFERENCES Ingredient(IngredientID),
	DrinkID INT NULL FOREIGN KEY REFERENCES Drink(DrinkID),
    StaffID INT NULL FOREIGN KEY REFERENCES Staff(StaffID),
    Type bit,
);

CREATE TABLE Buffet (
    BuffetID INT PRIMARY KEY IDENTITY(1,1),
    BuffetName NVARCHAR(255),
    BuffetPrice INT
);

CREATE TABLE Product (
    ProductID INT PRIMARY KEY IDENTITY(1,1),
    FoodID INT FOREIGN KEY REFERENCES Food(FoodID),
    BuffetID INT FOREIGN KEY REFERENCES Buffet(BuffetID),
    DrinkID INT FOREIGN KEY REFERENCES Drink(DrinkID),
    Price INT
);

CREATE TABLE Price_History (
    Price_HistoryID INT PRIMARY KEY IDENTITY(1,1),
    ProductID INT FOREIGN KEY REFERENCES Product(ProductID),
	Price INT,
    StartDate DATETIME NOT NULL,
    EndDate DATETIME 
);

CREATE TABLE Discount_Food (
	Discount_FoodID INT PRIMARY KEY IDENTITY(1,1),
    DiscountID INT FOREIGN KEY REFERENCES Discount(DiscountID),
    FoodID INT FOREIGN KEY REFERENCES Food(FoodID),
);

CREATE TABLE Buffet_Food (
	Buffet_FoodID INT PRIMARY KEY IDENTITY(1,1),
    BuffetID INT FOREIGN KEY REFERENCES Buffet(BuffetID),
    FoodID INT FOREIGN KEY REFERENCES Food(FoodID),
);

CREATE TABLE Buffet_Drink (
	Buffet_DrinkID INT PRIMARY KEY IDENTITY(1,1),
    BuffetID INT FOREIGN KEY REFERENCES Buffet(BuffetID),
    DrinkID INT FOREIGN KEY REFERENCES Drink(DrinkID),
);

CREATE TABLE Order_Food (
	Order_FoodID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT FOREIGN KEY REFERENCES [Order](OrderID),
    FoodID INT FOREIGN KEY REFERENCES Food(FoodID),
);

CREATE TABLE Order_Drink (
	Order_DrinkID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT FOREIGN KEY REFERENCES [Order](OrderID),
    DrinkID INT FOREIGN KEY REFERENCES Drink(DrinkID),
);

CREATE TABLE Order_Buffet (
	Order_BuffetID INT PRIMARY KEY IDENTITY(1,1),
    OrderID INT FOREIGN KEY REFERENCES [Order](OrderID),
    BuffetID INT FOREIGN KEY REFERENCES Buffet(BuffetID),
);

CREATE TABLE Ingredient_Food(
	Ingredient_FoodID INT PRIMARY KEY IDENTITY(1,1),
	FoodID INT FOREIGN KEY REFERENCES [Food](FoodID),
	IngredientID INT FOREIGN KEY REFERENCES [Ingredient](IngredientID),
)

CREATE TABLE [Shift] (
	ShiftID INT PRIMARY KEY IDENTITY(1,1),
	StartTime DATE,
	EndTime DATE,
	StaffQuantity int,
)

CREATE TABLE [Shift_Staff](
	Shift_StaffID INT PRIMARY KEY IDENTITY(1,1),
	ShiftID INT FOREIGN KEY REFERENCES [Shift](ShiftID),
	StaffID INT FOREIGN KEY REFERENCES [Staff](StaffID),
)

CREATE TABLE Responde (
	RespondeID INT PRIMARY KEY IDENTITY(1,1),
	RespondeNote NVARCHAR(255),
	FeedBackID INT FOREIGN KEY REFERENCES [FeedBack](FeedBackID),
	StaffID INT FOREIGN KEY REFERENCES [Staff](StaffID),
)
