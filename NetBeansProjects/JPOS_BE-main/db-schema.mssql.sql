USE [master]
GO
/****** Object:  Database [JPOS]    Script Date: 12/07/2024 5:14:47 am ******/
CREATE DATABASE [JPOS]
GO
ALTER DATABASE [JPOS] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [JPOS].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [JPOS] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [JPOS] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [JPOS] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [JPOS] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [JPOS] SET ARITHABORT OFF 
GO
ALTER DATABASE [JPOS] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [JPOS] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [JPOS] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [JPOS] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [JPOS] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [JPOS] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [JPOS] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [JPOS] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [JPOS] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [JPOS] SET  ENABLE_BROKER 
GO
ALTER DATABASE [JPOS] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [JPOS] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [JPOS] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [JPOS] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [JPOS] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [JPOS] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [JPOS] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [JPOS] SET RECOVERY FULL 
GO
ALTER DATABASE [JPOS] SET  MULTI_USER 
GO
ALTER DATABASE [JPOS] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [JPOS] SET DB_CHAINING OFF 
GO
ALTER DATABASE [JPOS] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [JPOS] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [JPOS] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [JPOS] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'JPOS', N'ON'
GO
ALTER DATABASE [JPOS] SET QUERY_STORE = ON
GO
ALTER DATABASE [JPOS] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [JPOS]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 12/07/2024 5:14:48 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CustomerRequests]    Script Date: 12/07/2024 5:14:48 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CustomerRequests](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cus_id] [int] NOT NULL,
	[description] [varchar](2000) NULL,
	[time] [datetime] NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 12/07/2024 5:14:48 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cus_id] [int] NOT NULL,
	[total_price] [float] NOT NULL,
	[time] [datetime] NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 12/07/2024 5:14:48 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cate_id] [int] NOT NULL,
	[name] [varchar](1000) NOT NULL,
	[description] [varchar](5000) NOT NULL,
	[image] [varchar](255) NULL,
	[price] [float] NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StaffResponses]    Script Date: 12/07/2024 5:14:48 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StaffResponses](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[request_id] [int] NOT NULL,
	[staff_id] [int] NOT NULL,
	[type] [varchar](255) NULL,
	[description] [varchar](5000) NULL,
	[time] [datetime] NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tokens]    Script Date: 12/07/2024 5:14:48 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tokens](
	[access_token] [varchar](255) NOT NULL,
	[user_id] [int] NOT NULL,
	[user_role] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[access_token] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 12/07/2024 5:14:48 am ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](100) NOT NULL,
	[fullname] [varchar](100) NOT NULL,
	[email] [varchar](100) NOT NULL,
	[password] [varchar](100) NOT NULL,
	[phone] [varchar](15) NULL,
	[address] [varchar](255) NULL,
	[role] [varchar](255) NULL,
	[status] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Categories] ON 
GO
INSERT [dbo].[Categories] ([id], [name]) VALUES (1, N'Necklaces')
GO
INSERT [dbo].[Categories] ([id], [name]) VALUES (2, N'Earrings')
GO
INSERT [dbo].[Categories] ([id], [name]) VALUES (3, N'Bracelets')
GO
INSERT [dbo].[Categories] ([id], [name]) VALUES (4, N'Rings')
GO
INSERT [dbo].[Categories] ([id], [name]) VALUES (5, N'Brooches')
GO
INSERT [dbo].[Categories] ([id], [name]) VALUES (6, N'Others')
GO
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[CustomerRequests] ON 
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (1, 3, N'test', CAST(N'2024-07-11T11:16:30.660' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (3, 3, N'test2', CAST(N'2024-07-11T11:19:23.140' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (4, 3, N'test3', CAST(N'2024-07-11T11:19:31.090' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (24, 3, N'test 4', CAST(N'2024-07-11T12:28:09.823' AS DateTime), N'Completed')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (25, 3, N'test4', CAST(N'2024-07-11T12:28:25.237' AS DateTime), N'Confirmed')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (26, 1002, N'test', CAST(N'2024-07-11T22:42:59.430' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (27, 1005, N'Ahihi d? ng?c', CAST(N'2024-07-11T22:44:17.377' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (28, 1005, N'Ahihi d? ng?c', CAST(N'2024-07-11T22:44:25.130' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (29, 1005, N'Ahihi d? ng?c', CAST(N'2024-07-11T22:44:41.547' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (30, 1005, N'Ahihi d? ng?c', CAST(N'2024-07-11T22:44:53.753' AS DateTime), N'Confirmed')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (31, 1005, N'Ahihi d? ng?c', CAST(N'2024-07-11T22:45:03.447' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (32, 1005, N'Ahihi d? ng?c', CAST(N'2024-07-11T22:45:32.343' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (33, 1005, N'Ahihi d? ng?c', CAST(N'2024-07-11T22:45:58.577' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (34, 1005, N'ajhihi d? ng?c', CAST(N'2024-07-11T22:46:21.310' AS DateTime), N'New')
GO
INSERT [dbo].[CustomerRequests] ([id], [cus_id], [description], [time], [status]) VALUES (35, 1002, N'test', CAST(N'2024-07-12T01:26:14.413' AS DateTime), N'Confirmed')
GO
SET IDENTITY_INSERT [dbo].[CustomerRequests] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 
GO
INSERT [dbo].[Orders] ([id], [cus_id], [total_price], [time], [status]) VALUES (1, 3, 1500, CAST(N'2024-07-12T04:54:41.570' AS DateTime), N'New')
GO
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (1, 1, N'T1 Circle Pendant', N'Bold and precisely crafted in 18k yellow gold with a beveled edge for a striking statement.', N'/images/products/1.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (2, 1, N'Smile Pendant', N'Simple, elegant, and modern design symbolizing happiness..', N'/images/products/2.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (3, 1, N'Medium Link Necklace', N'Inspired by a 1962 bracelet, it symbolizes love’s transformative strength with signature gauge links.', N'/images/products/3.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (4, 1, N'Graduated Link Necklace', N'A bold, graduated chain of gauge links embodying resilience and spirit.', N'/images/products/4.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (5, 1, N'Pendant', N'Featuring an oval link chain and hand-set diamonds, symbolizing enduring protection.', N'/images/products/5.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (6, 1, N'Pendant', N'Echoes a padlock motif with an oval link chain and hand-set diamonds, crafted in 18k yellow gold.', N'/images/products/6.jpg', 8760, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (7, 1, N'Pendant', N'Inspired by an archival bow from 1889, crafted in yellow gold for life’s enduring ties.', N'/images/products/7.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (8, 1, N'Pendant', N'Crafted with rose gold and pink sapphires, embodying love’s unwavering bonds.', N'/images/products/8.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (9, 1, N'Diamond Pendant', N'Features a single diamond in a delicate setting, part of the Diamonds by the Yard collection.', N'/images/products/9.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (10, 1, N'Diamond Pendant', N'A single diamond in a platinum setting, changing the role of diamonds in fashion.', N'/images/products/10.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (11, 1, N'Open Heart Pendant', N'Elsa Peretti''s iconic design celebrates the spirit of love with a simple and evocative shape.', N'/images/products/11.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (12, 1, N'Open Heart Pendant', N'Elsa Peretti''s iconic style crafted in 18k yellow gold and sterling silver, celebrating the spirit of love.', N'/images/products/12.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (13, 1, N'Solitaire Diamond Pendant', N'Features a round brilliant diamond on a 16-inch platinum chain, symbolizing timeless brilliance.', N'/images/products/13.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (14, 1, N'Solitaire Diamond Pendant', N'Legendary diamond pendant emphasizing superior cut, color, clarity, and brilliance.', N'/images/products/14.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (15, 1, N'Pendant', N'Platinum pendant with round brilliant diamonds, total carat weight .16, on a 16-inch chain.', N'/images/products/15.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (16, 1, N'Pendant', N'Combines round aquamarine and brilliant diamonds in platinum, highlighting elegance.', N'/images/products/16.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (17, 1, N'Small Link Necklace', N'Bold chain of gauge links inspired by a 1962 bracelet, symbolizing transformative strength.', N'/images/products/17.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (18, 1, N'Large Double Link Pendant', N'Bold gauge links crafted in a modern style, embodying resilience and spirit.', N'/images/products/18.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (19, 1, N'Medium Link Necklace', N'Features an open link motif crafted in sterling silver, celebrating superior craftsmanship.', N'/images/products/19.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (20, 1, N'Graduated Link Necklace', N'Sterling silver chain with a graduated link motif, representing superior craftsmanship and style.', N'/images/products/20.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (21, 2, N'Half Pavé Diamond Huggie Earrings', N'Features pavé diamonds on 18k yellow gold, highlighting the bold Tiffany T1 design.', N'/images/products/21.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (22, 2, N'Large Link Earrings', N'Double long link earrings featuring signature gauge link, symbolizing love''s transformative strength.', N'/images/products/22.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (23, 2, N'Pearl Hoop Earrings in Sterling Silver', N'Bold pearl wrapped in sterling silver, these hoop earrings symbolize strength and resilience.', N'/images/products/23.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (24, 2, N'Hoop Earrings', N'Modern interpretation of classic hoop design, part of the multifaceted Tiffany T collection.', N'/images/products/24.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (25, 2, N'Earrings', N'Lock design featuring a spring closure, crafted in 18k yellow gold to symbolize enduring protection.', N'/images/products/25.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (26, 2, N'Earrings', N'Lock design with a spring closure and hand-set diamonds, crafted in 18k yellow gold.', N'/images/products/26.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (27, 2, N'Earrings', N'Tiffany Knot design with yellow gold and pavé diamonds, embodying unwavering love bonds.', N'/images/products/27.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (28, 2, N'Drop Earrings', N'Crafted in white gold with round brilliant diamonds, Tiffany Knot symbolizes enduring connections.', N'/images/products/28.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (29, 2, N'Solitaire Diamond Stud Earrings', N'Round brilliant diamonds in platinum, meticulously matched for ultimate brilliance.', N'/images/products/29.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (30, 2, N'Solitaire Diamond Stud Earrings', N'Elegant stud earrings in 18k rose gold with round brilliant diamonds, symbolizing timeless elegance.', N'/images/products/30.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (31, 2, N'Hoop Earrings', N'Streamlined and modern, diamonds trace the classic shape, part of the Tiffany Metro collection.', N'/images/products/31.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (32, 2, N'Hoop Earrings', N'Part of the Tiffany Metro collection, featuring a modern design with diamonds tracing the classic hoop shape.', N'/images/products/32.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (33, 2, N'Pearls by the Yard Chain Earrings', N'Floating pearls on a delicate chain, embodying sleek treasures and luminescence.', N'/images/products/33.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (34, 2, N'Earrings', N'Akoya cultured pearls in 18k white gold, showcasing elegance with 7-7.5 mm pearls.', N'/images/products/34.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (35, 2, N'Sapphire and Diamond Earrings', N'Platinum earrings with sapphires and brilliant diamonds, highlighting luxury and brilliance.', N'/images/products/35.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (36, 2, N'Earrings', N'Featuring round aquamarines and brilliant diamonds in platinum, reflecting serene beauty.', N'/images/products/36.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (37, 2, N'Olive Leaf Climber Earrings', N'Designed to resemble an olive branch, symbolizing peace and abundance.', N'/images/products/37.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (38, 2, N'Olive Leaf Earrings', N'18k gold earrings with diamonds inspired by the olive branch, a peace and abundance symbol.', N'/images/products/38.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (39, 2, N'Earrings', N'Tiffany Victoria design, with a unique combination of diamond cuts for a romantic sensibility.', N'/images/products/39.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (40, 2, N'Lynn Earrings', N'Jean Schlumberger''s intricate design in timeless earrings with accentuating diamonds.', N'/images/products/40.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (41, 3, N'Wire Bracelet', N'Elegant Tiffany T collection design, embodying connections with a wire motif. Pair with other designs for sophistication.', N'/images/products/41.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (42, 3, N'T1 Narrow Hinged Bangle', N'Features a strong beveled ''T'' motif, symbolizing individual strength and power in a continuous circle.', N'/images/products/42.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (43, 3, N'Bangle', N'18k yellow and white gold with hand-set diamonds, inspired by a historic padlock symbolizing enduring protection.', N'/images/products/43.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (44, 3, N'Bangle', N'Inspired by a historic padlock, crafted in 18k yellow gold, embodying love’s enduring protection.', N'/images/products/44.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (45, 3, N'Pearl Lock Bracelet', N'Combines sleek sterling silver links with lustrous pearls, symbolizing strength and glamour.', N'/images/products/45.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (46, 3, N'Diamonds by the Yard Bracelet', N'Features bezel-set diamonds that shimmer against the skin, reinventing the classic diamond accessory.', N'/images/products/46.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (47, 3, N'Tiffany Solitaire Diamond Bracelet', N'Showcases legendary diamonds known for extraordinary cut, color, clarity, and brilliance.', N'/images/products/47.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (48, 3, N'Medium Link Bracelet', N'Signature gauge links form a bold chain, inspired by a 1962 bracelet, symbolizing love’s transformative strength.', N'/images/products/48.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (49, 3, N'Interlocking Circles Chain Bracelet', N'18k gold bracelet with adjustable length, featuring interlocking circles for a sleek look.', N'/images/products/49.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (50, 3, N'Interlocking Circles Chain Bracelet', N'Heritage-inspired design from the Tiffany 1837® collection, featuring sleek curves and interlocking circles.', N'/images/products/50.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (51, 3, N'Heart Tag Toggle Bracelet', N'Classic reinvented with an engraved tag and toggle closure, inspired by the iconic key ring from 1969.', N'/images/products/51.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (52, 3, N'Heart Tag Bead Bracelet', N'Combines an elegant bead bracelet with an engraved tag, inspired by the iconic key ring from 1969.', N'/images/products/52.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (53, 3, N'Diamonds by the Yard Bracelet', N'Delicate bracelet in platinum with a center diamond and aquamarine drop, part of Elsa Peretti''s revolutionary collection.', N'/images/products/53.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (54, 3, N'Color by the Yard Bracelet', N'Sterling silver bracelet featuring three round aquamarines, total carat weight .18.', N'/images/products/54.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (55, 3, N'Olive Leaf Pearl Heart Bracelet', N'Sterling silver bracelet with freshwater cultured pearls, symbolizing peace and abundance.', N'/images/products/55.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (56, 3, N'Diamond Wire Bracelet', N'Timeless bracelet enhanced with brilliant diamonds, part of the iconic Tiffany T collection.', N'/images/products/57.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (57, 3, N'Smile Bracelet', N'Features a subtle curve embodying happiness, part of the Tiffany T smile collection.', N'/images/products/58.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (58, 3, N'Pave Diamond Wire Bangle', N'Pavé diamonds on a timeless bangle, emphasizing elegance and iconic design.', N'/images/products/59.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (59, 3, N'T1 Narrow Pave Diamond Hinged Bangle', N'Features scintillating pavé diamonds and a strong T motif, symbolizing strength and power.', N'/images/products/60.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (60, 4, N'Diamond Vine Ring in Platinum', N'Celebrates Tiffany diamonds'' brilliance with an organic vine motif and mixed-cut diamonds.', N'/images/products/61.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (61, 4, N'Alternating Ring', N'Features a unique combination of cuts for a romantic sensibility and subtle glamour.', N'/images/products/62.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (62, 4, N'Wire Ring', N'Bold ''T'' motif ring from the Tiffany T collection, stunning in simplicity.', N'/images/products/63.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (63, 4, N'T1 Narrow Ring', N'Pavé diamonds add striking radiance, representing strength and power in the Tiffany T1 collection.', N'/images/products/64.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (64, 4, N'True Wide Ring', N'Features graphic angles with pavé diamonds, creating a bold, modern look in the Tiffany T collection.', N'/images/products/65.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (65, 4, N'Diamond Wire Band Ring', N'Sleek design illuminated with brilliant diamonds from the iconic Tiffany T collection.', N'/images/products/66.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (66, 4, N'V Ring', N'Designed for stacking, crafted with clean lines and scintillating diamonds.', N'/images/products/67.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (67, 4, N'Half Eternity Ring', N'Platinum band with a half circle of round brilliant diamonds, emphasizing elegance.', N'/images/products/68.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (68, 4, N'Band Ring', N'Radiant platinum band with a half-circle of round brilliant diamonds, celebrating lifelong commitment.', N'/images/products/69.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (69, 4, N'Band Ring', N'Platinum band with a half-circle of diamonds and sapphires, designed for a bold statement.', N'/images/products/70.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (70, 4, N'Band Ring', N'Tiffany Harmony® band ring, meticulously crafted in platinum with round brilliant diamonds, designed to nest perfectly.', N'/images/products/71.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (71, 4, N'Band Ring', N'Features intricate beveled edges, perfect as a wedding band or for bold stacking.', N'/images/products/72.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (72, 4, N'Love Ring', N'Designed with Paloma Picasso’s handwriting, inspired by New York City street art, perfect for bold styling.', N'/images/products/73.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (73, 4, N'Love Ring', N'Elegant design featuring Paloma Picasso’s handwriting, inspired by New York graffiti.', N'/images/products/74.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (74, 4, N'Olive Leaf Band Ring', N'Sculpted leaves in 18k gold, a tribute to the olive branch, symbolizing peace and abundance.', N'/images/products/75.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (75, 4, N'Olive Leaf Bypass Ring', N'Honors the olive branch as a symbol of peace and abundance in a bypass design.', N'/images/products/76.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (76, 4, N'Stacking Band Ring', N'Designed for stacking, crafted in 18k gold with brilliant diamonds, showcasing subtle elegance.', N'/images/products/77.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (77, 4, N'Stacking Band Ring', N'Elsa Peretti® design with brilliant diamonds, ideal for modern, subtle stacking.', N'/images/products/78.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (78, 4, N'Wave Ring', N'Recalls the undulating sea with fluid lines in 18k gold, inspired by Elsa Peretti’s love of the natural world.', N'/images/products/79.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (79, 4, N'Wave Five-row Ring', N'Fluid lines inspired by the sea, featuring an aquamarine center in a dynamic five-row design.', N'/images/products/80.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (80, 5, N'Open Heart Brooch', N'Iconic Elsa Peretti design in platinum with pavé diamonds, celebrating the spirit of love.', N'/images/products/81.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (81, 5, N'Open Heart Brooch', N'Iconic Elsa Peretti design in yellow gold with pavé diamonds, ideal for an eye-catching look.', N'/images/products/82.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (82, 5, N'Bird on a Rock Brooch', N'Iconic 1965 design featuring a bird and a Fancy Intense Yellow diamond, epitomizing Tiffany''s craftsmanship.', N'/images/products/83.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (83, 5, N'Bird on a Rock Brooch', N'1965 design with a bird set on a Fancy Intense Yellow diamond, showcasing superior craftsmanship.', N'/images/products/84.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (84, 5, N'Paris Flames Brooch', N'Intricate design in 18k gold and platinum, mimicking the movement of flames with diamond accents.', N'/images/products/85.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (85, 5, N'Apollo Brooch', N'Inspired by electrons around an atom, features diamonds and gold accents in a striking design.', N'/images/products/86.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (86, 5, N'Ibex Brooch', N'Inspired by the natural world, in 18k gold and platinum with diamonds and a pink sapphire.', N'/images/products/87.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (87, 5, N'Peapod Brooch', N'Features gold leaves and cultured pearls, transforming a peapod into a striking design.', N'/images/products/88.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (88, 5, N'Maltese Cross Clip', N'Timeless design inspired by the symbol of Almalfi, showcasing Schlumberger’s intricate craftsmanship.', N'/images/products/89.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (89, 5, N'Fleur de Lis Key Brooch', N'Tiffany Keys design inspired by the fleur de lis motif, adding elegance to any outfit.', N'/images/products/90.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (90, 5, N'Fleur de Lis Key Brooch', N'Radiant symbol of a bright future, inspired by the fleur de lis key motif.', N'/images/products/91.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (91, 5, N'Starfish Brooch', N'Evokes the mystery of the sea and sky, in platinum with pavé diamonds.', N'/images/products/92.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (92, 5, N'Amapola Brooch', N'Elsa Peretti''s interpretation of the poppy flower, crafted in fine metal and silk.', N'/images/products/93.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (93, 5, N'Amapola Brooch', N'Handmade silk and sterling silver design, reflecting quality craftsmanship.', N'/images/products/94.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (94, 5, N'Amapola Brooch', N'Symbolizing the poppy flower, crafted in 18k gold and red silk.', N'/images/products/95.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (95, 5, N'Fleur de Mer Clip', N'Combines sapphires and diamonds to mimic a sea flower, in platinum and 18k gold.', N'/images/products/96.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (96, 5, N'Spiritual Dreamcatcher Charm', N'Inspired by the Native American dream catcher, crafted in sterling silver with feather details.', N'/images/products/97.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (97, 5, N'Game of Thrones Needle Dangle Charm', N'Represents Arya Stark''s dagger Needle, crafted in sterling silver and 14k gold, with ''NOT TODAY'' engraving.', N'/images/products/98.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (98, 5, N'Game of Thrones Ice & Fire Dragons Dual Murano Glass Charm', N'Murano glass charm depicting Ice and Fire dragons, for Game of Thrones fans.', N'/images/products/99.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (99, 5, N'Cut-out Sparkling Star Charm', N'Inspired by Pandora’s vintage charms, combining star-shaped cut-outs with cubic zirconia stones.', N'/images/products/100.jpg', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (100, 6, N'Customized Design Accessories', N'Customezid design.', N'', 0, N'Actived')
GO
INSERT [dbo].[Products] ([id], [cate_id], [name], [description], [image], [price], [status]) VALUES (101, 6, N'Customized Design Accessories', N'Customezid design.', N'', 0, N'Actived')
GO
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
SET IDENTITY_INSERT [dbo].[StaffResponses] ON 
GO
INSERT [dbo].[StaffResponses] ([id], [request_id], [staff_id], [type], [description], [time], [status]) VALUES (1, 24, 3, NULL, N'', CAST(N'2024-07-12T02:06:56.090' AS DateTime), N'Completed')
GO
INSERT [dbo].[StaffResponses] ([id], [request_id], [staff_id], [type], [description], [time], [status]) VALUES (2, 24, 3, NULL, N'Ahihi d? ng?c', CAST(N'2024-07-12T02:10:42.703' AS DateTime), N'New')
GO
INSERT [dbo].[StaffResponses] ([id], [request_id], [staff_id], [type], [description], [time], [status]) VALUES (3, 25, 3, NULL, N'', CAST(N'2024-07-12T03:01:45.727' AS DateTime), N'New')
GO
INSERT [dbo].[StaffResponses] ([id], [request_id], [staff_id], [type], [description], [time], [status]) VALUES (4, 35, 3, NULL, N'', CAST(N'2024-07-12T03:04:34.133' AS DateTime), N'New')
GO
INSERT [dbo].[StaffResponses] ([id], [request_id], [staff_id], [type], [description], [time], [status]) VALUES (5, 30, 1014, NULL, N'test manager', CAST(N'2024-07-12T03:52:39.533' AS DateTime), N'New')
GO
SET IDENTITY_INSERT [dbo].[StaffResponses] OFF
GO
INSERT [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (N'7sFKE079PYtpUYvyMp4JFNu54V28Kh9sP1O8ZAnaOCVDfrDn4kbOl6hND2J8Vmds', 1, N'Admin')
GO
INSERT [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (N'd5Ko6nVASQrrdVmUDtMyKYEilwzMJ2ddZLYLYJ3iYDRpCTzaPP4A4l2P76671m1p', 1, N'Admin')
GO
INSERT [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (N'DHmxYlei73fm3h2r2CjoTx59aWPkCT2pqdPD9unAK4ekkjtuoxmSwLO7NcOP0Hd9', 3, N'Customer')
GO
INSERT [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (N'gcHIDrqWZeJQ4pnncNFqho8gDgNq2by9VrcalEdYP5YXe3y2nMGPSagOfLxUBx5r', 3, N'Customer')
GO
INSERT [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (N'Gp60W8vtakLKefSMoSG6jETK9Uegg2N0OhS9IoGHcjsUDjrbp3YTgpfRGZ2CxDwB', 1014, N'Sale Staff')
GO
INSERT [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (N'IdY69hpsSgyzb0VxMeX3dWUgWpnjugKhAoMUvG5hlfI7VrVEtRUZBsFxvdnCQdGH', 3, N'Customer')
GO
INSERT [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (N'OK3DbnWtsfCbcdxLrc5eW33aOWLMyqVWD7v0GfaPYFwi1N8vYZ3mK8ZlkssIP3aA', 1014, N'Sale Staff')
GO
INSERT [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (N'Qh7QMhLcOQdNKBNJ7S21ywvrzYWkKAnVO2fEGqhmw9KBYGzCapaKNxtzK1iLPZcE', 1, N'Admin')
GO
INSERT [dbo].[Tokens] ([access_token], [user_id], [user_role]) VALUES (N's3aOGvC5XYk6oNrONvO1gtMsQ9YFi6Cfl1y7w4NZuKQ0qTKphc7LpYeijPkYGT2C', 1, N'Admin')
GO
SET IDENTITY_INSERT [dbo].[Users] ON 
GO
INSERT [dbo].[Users] ([id], [username], [fullname], [email], [password], [phone], [address], [role], [status]) VALUES (1, N'admin', N'admin', N'admin@jpos.com', N'admin', NULL, NULL, N'Admin', N'Active')
GO
INSERT [dbo].[Users] ([id], [username], [fullname], [email], [password], [phone], [address], [role], [status]) VALUES (3, N'thucnee', N'thucnee', N'thucnee@gmail.com', N'123456', NULL, NULL, N'Customer', N'Actived')
GO
INSERT [dbo].[Users] ([id], [username], [fullname], [email], [password], [phone], [address], [role], [status]) VALUES (1002, N'test4', N'test4', N'test', N'1234', N'03123', NULL, N'Customer', N'Actived')
GO
INSERT [dbo].[Users] ([id], [username], [fullname], [email], [password], [phone], [address], [role], [status]) VALUES (1005, N'Quy?n Ph?m Qu?c', N'Quy?n Ph?m Qu?c', N'gobyysamaa@gmail.com', N'1234', N'0336189732', NULL, N'Customer', N'Actived')
GO
INSERT [dbo].[Users] ([id], [username], [fullname], [email], [password], [phone], [address], [role], [status]) VALUES (1014, N'salestaff', N'salestaff', N'stafstaff@jpos.com', N'staff', N'0966316803', NULL, N'Sale Staff', N'Actived')
GO
INSERT [dbo].[Users] ([id], [username], [fullname], [email], [password], [phone], [address], [role], [status]) VALUES (1015, N'productstaff', N'productstaff', N'productstaff@jpos.com', N'staff', N'0966316803', NULL, N'Product Staff', N'Actived')
GO
INSERT [dbo].[Users] ([id], [username], [fullname], [email], [password], [phone], [address], [role], [status]) VALUES (1016, N'designstaff', N'designstaff', N'designstaff@jpos.com', N'staff', N'0966316803', NULL, N'Design Staff', N'Actived')
GO
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
ALTER TABLE [dbo].[CustomerRequests] ADD  DEFAULT (getdate()) FOR [time]
GO
ALTER TABLE [dbo].[CustomerRequests] ADD  DEFAULT ('New') FOR [status]
GO
ALTER TABLE [dbo].[Orders] ADD  DEFAULT (getdate()) FOR [time]
GO
ALTER TABLE [dbo].[Orders] ADD  DEFAULT ('New') FOR [status]
GO
ALTER TABLE [dbo].[Products] ADD  DEFAULT ('Actived') FOR [status]
GO
ALTER TABLE [dbo].[StaffResponses] ADD  DEFAULT (getdate()) FOR [time]
GO
ALTER TABLE [dbo].[StaffResponses] ADD  DEFAULT ('New') FOR [status]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ('Actived') FOR [status]
GO
ALTER TABLE [dbo].[CustomerRequests]  WITH CHECK ADD  CONSTRAINT [FK_CustomerRequests_Users] FOREIGN KEY([cus_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[CustomerRequests] CHECK CONSTRAINT [FK_CustomerRequests_Users]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Users] FOREIGN KEY([cus_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Users]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Categories] FOREIGN KEY([cate_id])
REFERENCES [dbo].[Categories] ([id])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Categories]
GO
ALTER TABLE [dbo].[StaffResponses]  WITH CHECK ADD  CONSTRAINT [FK_StaffResponses_CustomerRequests] FOREIGN KEY([request_id])
REFERENCES [dbo].[CustomerRequests] ([id])
GO
ALTER TABLE [dbo].[StaffResponses] CHECK CONSTRAINT [FK_StaffResponses_CustomerRequests]
GO
ALTER TABLE [dbo].[StaffResponses]  WITH CHECK ADD  CONSTRAINT [FK_StaffResponses_Users] FOREIGN KEY([staff_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[StaffResponses] CHECK CONSTRAINT [FK_StaffResponses_Users]
GO
ALTER TABLE [dbo].[Tokens]  WITH CHECK ADD  CONSTRAINT [FK_Tokens_Users] FOREIGN KEY([user_id])
REFERENCES [dbo].[Users] ([id])
GO
ALTER TABLE [dbo].[Tokens] CHECK CONSTRAINT [FK_Tokens_Users]
GO
USE [master]
GO
ALTER DATABASE [JPOS] SET  READ_WRITE 
GO
