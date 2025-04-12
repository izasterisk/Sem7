CREATE DATABASE PizzaStore
GO

USE PizzaStore
GO

CREATE TABLE Suppliers (
	SupplierID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	CompanyName NVARCHAR(MAX),
	[Address] NVARCHAR(MAX),
	Phone VARCHAR(50),
)



INSERT INTO Suppliers VALUES ('Pizza Company', N'Công ty Cổ phần Pizza Ngon 77 Trần Nhân Tôn, Phường 9, Quận 5, Thành phố Hồ Chí Minh.','02873083377')
INSERT INTO Suppliers VALUES ('Pizza Hut', N'Tầng 10, Tòa nhà Opal Office, số 92 Nguyễn Hữu Cảnh, Phường 22, Quận Bình Thạnh, TP.HCM, Việt Nam.','0303902751')
INSERT INTO Suppliers VALUES ('Pizza Pasta Paradise', N'242 NGUYỄN THỊ MINH KHAI, PHƯỜNG 6,QUẬN 3,TP.HCM.','0909292226')
INSERT INTO Suppliers (CompanyName, Address, Phone) VALUES ('Pizza Dream', N'123 Đường Nguyễn Đình Chiểu, Phường 1, Quận 3, TP.HCM', '0281234567');
INSERT INTO Suppliers (CompanyName, Address, Phone) VALUES ('Italiano Pizza', N'456 Đường Lê Lợi, Phường 5, Quận 1, TP.HCM', '0287654321');
INSERT INTO Suppliers (CompanyName, Address, Phone) VALUES ('Mama Mia Pizzeria', N'789 Đường Trần Hưng Đạo, Phường 11, Quận 5, TP.HCM', '0289876543');
INSERT INTO Suppliers (CompanyName, Address, Phone) VALUES ('Sicilian Slice', N'101 Đường Lý Thường Kiệt, Phường 7, Quận 10, TP.HCM', '0284567891');


CREATE TABLE Categories (
	CategoryID NVARCHAR(50) NOT NULL PRIMARY KEY,
	CategoryName NVARCHAR(MAX),
	[Description] NVARCHAR(MAX),
)

INSERT INTO Categories VALUES ('PZZ', N'Pizza', N'Pizza là một biểu tượng của ẩm thực Ý, và nó đã trở thành một phần quan trọng trong nền văn hóa ẩm thực toàn cầu. Bản chất của Pizza Truyền Thống nằm ở sự kết hợp hài hòa giữa bánh pizza mỏng, giòn và nhân đầy đặn hương vị.')
INSERT INTO Categories VALUES ('PST', N'Pasta', N'Pasta là một trong những món ăn truyền thống của Ý, đa dạng về hình dạng và cách chế biến. Món ăn này phản ánh sự tinh tế và đa dạng của ẩm thực Ý, từ spaghetti, fettuccine đến ravioli và lasagna.');
INSERT INTO Categories VALUES ('DRNK', N'Drinks', N'Đồ uống kết hợp hài hòa với các món ăn, từ nước lọc, soda đến rượu vang và cocktail. Chúng tạo nên sự trọn vẹn cho bữa ăn qua hương vị và cảm giác.');
INSERT INTO Categories VALUES ('BRG', N'Burger', N'Burger là một trong những món ăn nhanh phổ biến nhất trên thế giới, đặc trưng bởi sự kết hợp của bánh mì, thịt (thường là bò) nướng và các loại rau, phô mai, và sốt. Nó đại diện cho lối sống năng động, hiện đại và là lựa chọn lý tưởng cho bữa ăn nhanh chóng nhưng vẫn ngon miệng.')

CREATE TABLE Account (
	AccountID int Identity(1,1)PRIMARY KEY NOT NULL,
	userName VARCHAR(20) NOT NULL,
	[password] VARCHAR(50) NOT NULL,
	fullName NVARCHAR(50) NOT NULL,
	[role] INT
)


INSERT INTO Account VALUES ('U001', '123', 'Tom', 1)
INSERT INTO Account VALUES ('U002', '456', 'David', 2)
INSERT INTO Account VALUES ('U003', '789', 'John', 2)
INSERT INTO Account VALUES ('U004', '012', 'Mark', 2)
INSERT INTO Account VALUES ('U005', '134', 'Kate', 2)

CREATE TABLE [Customers] (
	CustomerID INT IDENTITY (1, 1)NOT NULL PRIMARY KEY,
	AccountID int not null,
	ContactName NVARCHAR(200),
	[Address] NVARCHAR(MAX),
	Phone VARCHAR(20),
	CONSTRAINT FK_AccountID FOREIGN KEY (AccountID) REFERENCES Account(AccountID)
)

INSERT INTO [Customers] VALUES ( 1, N'Hứa Đình Thuận', N'Vinhome', '0903187194')

CREATE TABLE [Orders] (
	OrderID INT IDENTITY(1, 1)NOT NULL PRIMARY KEY,
	CustomerID INT NOT NULL,
	OrderDate DATETIME,
	ShippedDate DATETIME,
	Freight FLOAT,
	ShipAddress NVARCHAR(500),
	[status] bit,
	CONSTRAINT FK_CustomerID FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
)
INSERT INTO [Orders] VALUES(1, '2024-03-05', '2024-03-08', 3.2, N'Vinhome Grand Park', 'true')


CREATE TABLE Products (
	ProductID INT IDENTITY(1, 1) NOT NULL PRIMARY KEY,
	ProductName NVARCHAR(MAX),
	SupplierID INT NOT NULL,
	CategoryID NVARCHAR(50) NOT NULL,
	QuantityPerUnit INT,
	UnitPrice FLOAT,
	ProductImage NVARCHAR(MAX),
	[Description] NVARCHAR(MAX),
	CONSTRAINT FK_SupplierID FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
	CONSTRAINT FK_CategoryID FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID),
)

INSERT INTO [Products] VALUES('Italian Pizza', 1 ,'PZZ', 50 , 2.90,'images/pizza-1.jpg',N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia ')
INSERT INTO [Products] VALUES('Greek Pizza', 2 ,'PZZ', 50 , 2.90,'images/pizza-2.jpg',N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia ')
INSERT INTO [Products] VALUES('Caucasian Pizza', 2 ,'PZZ', 50 , 2.90,'images/pizza-3.jpg', N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia ')
INSERT INTO [Products] VALUES('American Pizza', 2 ,'PZZ', 50 , 2.90,'images/pizza-4.jpg', N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia ')
INSERT INTO [Products] VALUES('Tomatoe Pie', 2 ,'PZZ', 50 , 2.90,'images/pizza-5.jpg', N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia ')
INSERT INTO [Products] VALUES('Margherita', 2 ,'PZZ', 50 , 2.90,'images/pizza-6.jpg', N'Far far away, behind the word mountains, far from the countries Vokalia and Consonantia ')


CREATE TABLE OrdersDetails(
	OrderID INT NOT NULL,
	ProductID INT NOT NULL,
	UnitPrice FLOAT,
	Quantity INT,
	PRIMARY KEY (OrderID, ProductID),
	CONSTRAINT FK_ProductID FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
	CONSTRAINT FK_OrderID FOREIGN KEY (OrderID) REFERENCES Orders(OrderID) 
)

INSERT INTO OrdersDetails VALUES (1, 1, 2.90, 2)
INSERT INTO OrdersDetails VALUES (1, 2, 2.90, 1)
INSERT INTO OrdersDetails VALUES (1, 3, 2.90, 3)