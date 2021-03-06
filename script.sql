USE [master]
GO
/****** Object:  Database [PharmacyManagementSystem]    Script Date: 9/22/2020 2:10:06 PM ******/
CREATE DATABASE [PharmacyManagementSystem]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PharmacyManagementSystem', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\PharmacyManagementSystem.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'PharmacyManagementSystem_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\PharmacyManagementSystem_log.ldf' , SIZE = 1072KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [PharmacyManagementSystem] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PharmacyManagementSystem].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PharmacyManagementSystem] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET ARITHABORT OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [PharmacyManagementSystem] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PharmacyManagementSystem] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PharmacyManagementSystem] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET  ENABLE_BROKER 
GO
ALTER DATABASE [PharmacyManagementSystem] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PharmacyManagementSystem] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [PharmacyManagementSystem] SET  MULTI_USER 
GO
ALTER DATABASE [PharmacyManagementSystem] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PharmacyManagementSystem] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PharmacyManagementSystem] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PharmacyManagementSystem] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [PharmacyManagementSystem] SET DELAYED_DURABILITY = DISABLED 
GO
USE [PharmacyManagementSystem]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 9/22/2020 2:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Customer](
	[Customer_id] [int] IDENTITY(5000,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[ContactNumber] [varchar](11) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Customer_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[ContactNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 9/22/2020 2:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Employee](
	[Employee_id] [int] IDENTITY(2000,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Password] [varchar](10) NOT NULL,
	[Contact Number] [varchar](11) NOT NULL,
	[Address] [varchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Employee_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Contact Number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Medicine]    Script Date: 9/22/2020 2:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Medicine](
	[Medicine_id] [int] IDENTITY(3000,1) NOT NULL,
	[MedicineName] [varchar](50) NOT NULL,
	[UnitPrice] [float] NOT NULL,
	[Weight] [varchar](20) NULL,
	[Quantity] [int] NULL,
	[CompanyName] [varchar](50) NOT NULL,
	[GenericName] [varchar](50) NULL,
 CONSTRAINT [pk_MCConstraint] PRIMARY KEY CLUSTERED 
(
	[Medicine_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [pk_UMNameWeightCNameConstraint] UNIQUE NONCLUSTERED 
(
	[MedicineName] ASC,
	[Weight] ASC,
	[CompanyName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Owner]    Script Date: 9/22/2020 2:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Owner](
	[Owner_id] [int] IDENTITY(1000,1) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Password] [varchar](10) NOT NULL,
	[Contact Number] [varchar](11) NOT NULL,
	[Address] [varchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Owner_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Contact Number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PreOrder]    Script Date: 9/22/2020 2:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PreOrder](
	[Order_id] [int] IDENTITY(6000,1) NOT NULL,
	[Medicine_id] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Customer_id] [int] NOT NULL,
	[is_Pending] [varchar](3) NULL DEFAULT ('YES'),
PRIMARY KEY CLUSTERED 
(
	[Order_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PROVIDE]    Script Date: 9/22/2020 2:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PROVIDE](
	[Provide_id] [int] IDENTITY(1,1) NOT NULL,
	[Medicine_id] [int] NOT NULL,
	[Batch_no] [varchar](20) NOT NULL,
	[Quantity] [int] NOT NULL,
	[ExpireDate] [date] NOT NULL,
	[Shelf_no] [varchar](20) NOT NULL,
	[Supplier_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Provide_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [pk_UConstraint] UNIQUE NONCLUSTERED 
(
	[Medicine_id] ASC,
	[Batch_no] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[SCHEDULE]    Script Date: 9/22/2020 2:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SCHEDULE](
	[Schedule_id] [int] IDENTITY(7000,1) NOT NULL,
	[Date] [date] NOT NULL,
	[Medicine_id] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
	[Customer_id] [int] NOT NULL,
	[is_Pending] [varchar](3) NULL DEFAULT ('YES'),
PRIMARY KEY CLUSTERED 
(
	[Schedule_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [pk_SDate_CId_Mid_Constraint] UNIQUE NONCLUSTERED 
(
	[Date] ASC,
	[Customer_id] ASC,
	[Medicine_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Sell]    Script Date: 9/22/2020 2:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sell](
	[Record_id] [int] IDENTITY(1,1) NOT NULL,
	[Time] [datetime] NOT NULL,
	[Employee_id] [int] NULL,
	[Owner_id] [int] NULL,
	[Provide_id] [int] NULL,
	[Quantity] [int] NOT NULL,
	[TotalCost] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[Record_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[SUPPLIER]    Script Date: 9/22/2020 2:10:06 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[SUPPLIER](
	[Supplier_id] [int] IDENTITY(4000,1) NOT NULL,
	[SupplierName] [varchar](50) NOT NULL,
	[ContactNumber] [varchar](11) NOT NULL,
	[Address] [varchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[Supplier_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[ContactNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[PreOrder]  WITH CHECK ADD FOREIGN KEY([Customer_id])
REFERENCES [dbo].[Customer] ([Customer_id])
GO
ALTER TABLE [dbo].[PreOrder]  WITH CHECK ADD FOREIGN KEY([Medicine_id])
REFERENCES [dbo].[Medicine] ([Medicine_id])
GO
ALTER TABLE [dbo].[PROVIDE]  WITH CHECK ADD FOREIGN KEY([Medicine_id])
REFERENCES [dbo].[Medicine] ([Medicine_id])
GO
ALTER TABLE [dbo].[PROVIDE]  WITH CHECK ADD FOREIGN KEY([Supplier_id])
REFERENCES [dbo].[SUPPLIER] ([Supplier_id])
GO
ALTER TABLE [dbo].[SCHEDULE]  WITH CHECK ADD FOREIGN KEY([Customer_id])
REFERENCES [dbo].[Customer] ([Customer_id])
GO
ALTER TABLE [dbo].[SCHEDULE]  WITH CHECK ADD FOREIGN KEY([Medicine_id])
REFERENCES [dbo].[Medicine] ([Medicine_id])
GO
ALTER TABLE [dbo].[Sell]  WITH CHECK ADD FOREIGN KEY([Employee_id])
REFERENCES [dbo].[Employee] ([Employee_id])
GO
ALTER TABLE [dbo].[Sell]  WITH CHECK ADD FOREIGN KEY([Owner_id])
REFERENCES [dbo].[Owner] ([Owner_id])
GO
ALTER TABLE [dbo].[Sell]  WITH CHECK ADD FOREIGN KEY([Provide_id])
REFERENCES [dbo].[PROVIDE] ([Provide_id])
GO
ALTER TABLE [dbo].[Medicine]  WITH CHECK ADD CHECK  (([Quantity]>=(0)))
GO
ALTER TABLE [dbo].[Medicine]  WITH CHECK ADD CHECK  (([UnitPrice]>(0)))
GO
ALTER TABLE [dbo].[PreOrder]  WITH CHECK ADD CHECK  (([Quantity]>(0)))
GO
ALTER TABLE [dbo].[PROVIDE]  WITH CHECK ADD CHECK  (([Quantity]>=(0)))
GO
ALTER TABLE [dbo].[PROVIDE]  WITH CHECK ADD CHECK  (([Quantity]>=(0)))
GO
ALTER TABLE [dbo].[SCHEDULE]  WITH CHECK ADD CHECK  (([Quantity]>(0)))
GO
ALTER TABLE [dbo].[Sell]  WITH CHECK ADD CHECK  (([Quantity]>(0)))
GO
USE [master]
GO
ALTER DATABASE [PharmacyManagementSystem] SET  READ_WRITE 
GO
