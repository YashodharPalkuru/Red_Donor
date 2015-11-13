

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_dbo.SubDistricts_dbo.Districts_DistrictId]') AND parent_object_id = OBJECT_ID(N'[dbo].[SubDistricts]'))
ALTER TABLE [dbo].[SubDistricts] DROP CONSTRAINT [FK_dbo.SubDistricts_dbo.Districts_DistrictId]

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_dbo.States_dbo.Countries_CountryId]') AND parent_object_id = OBJECT_ID(N'[dbo].[States]'))
ALTER TABLE [dbo].[States] DROP CONSTRAINT [FK_dbo.States_dbo.Countries_CountryId]

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_dbo.Districts_dbo.States_StateId]') AND parent_object_id = OBJECT_ID(N'[dbo].[Districts]'))
ALTER TABLE [dbo].[Districts] DROP CONSTRAINT [FK_dbo.Districts_dbo.States_StateId]

/****** Object:  Table [dbo].[SubDistricts]    Script Date: 7/3/2015 10:45:26 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SubDistricts]') AND type in (N'U'))
DROP TABLE [dbo].[SubDistricts]

/****** Object:  Table [dbo].[States]    Script Date: 7/3/2015 10:45:26 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[States]') AND type in (N'U'))
DROP TABLE [dbo].[States]

/****** Object:  Table [dbo].[Districts]    Script Date: 7/3/2015 10:45:26 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Districts]') AND type in (N'U'))
DROP TABLE [dbo].[Districts]

/****** Object:  Table [dbo].[Countries]    Script Date: 7/3/2015 10:45:26 AM ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Countries]') AND type in (N'U'))
DROP TABLE [dbo].[Countries]

/****** Object:  Table [dbo].[Countries]    Script Date: 7/3/2015 10:45:26 AM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Countries]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Countries](
	[CountryId] [int] IDENTITY(1,1) NOT NULL,
	[CountryName] [nvarchar](max) NOT NULL,
 CONSTRAINT [PK_dbo.Countries] PRIMARY KEY CLUSTERED 
(
	[CountryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END

/****** Object:  Table [dbo].[Districts]    Script Date: 7/3/2015 10:45:26 AM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Districts]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[Districts](
	[DistrictId] [int] IDENTITY(1,1) NOT NULL,
	[DistrictName] [nvarchar](max) NULL,
	[StateId] [int] NOT NULL,
 CONSTRAINT [PK_dbo.Districts] PRIMARY KEY CLUSTERED 
(
	[DistrictId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END

/****** Object:  Table [dbo].[States]    Script Date: 7/3/2015 10:45:26 AM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[States]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[States](
	[StateId] [int] IDENTITY(1,1) NOT NULL,
	[StateName] [nvarchar](max) NOT NULL,
	[CountryId] [int] NOT NULL,
 CONSTRAINT [PK_dbo.States] PRIMARY KEY CLUSTERED 
(
	[StateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END

/****** Object:  Table [dbo].[SubDistricts]    Script Date: 7/3/2015 10:45:26 AM ******/
SET ANSI_NULLS ON

SET QUOTED_IDENTIFIER ON

IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SubDistricts]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[SubDistricts](
	[SubDistrictsId] [int] IDENTITY(1,1) NOT NULL,
	[SubDistrictsName] [nvarchar](max) NOT NULL,
	[DistrictId] [int] NOT NULL,
 CONSTRAINT [PK_dbo.SubDistricts] PRIMARY KEY CLUSTERED 
(
	[SubDistrictsId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
END

SET IDENTITY_INSERT [dbo].[Countries] ON 


INSERT [dbo].[Countries] ([CountryId], [CountryName]) VALUES (1, N'India')

SET IDENTITY_INSERT [dbo].[Countries] OFF

SET IDENTITY_INSERT [dbo].[Districts] ON 




SET IDENTITY_INSERT [dbo].[Districts] OFF

SET IDENTITY_INSERT [dbo].[States] ON 



SET IDENTITY_INSERT [dbo].[States] OFF

SET IDENTITY_INSERT [dbo].[SubDistricts] ON 




SET IDENTITY_INSERT [dbo].[SubDistricts] OFF

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_dbo.Districts_dbo.States_StateId]') AND parent_object_id = OBJECT_ID(N'[dbo].[Districts]'))
ALTER TABLE [dbo].[Districts]  WITH CHECK ADD  CONSTRAINT [FK_dbo.Districts_dbo.States_StateId] FOREIGN KEY([StateId])
REFERENCES [dbo].[States] ([StateId])
ON DELETE CASCADE

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_dbo.Districts_dbo.States_StateId]') AND parent_object_id = OBJECT_ID(N'[dbo].[Districts]'))
ALTER TABLE [dbo].[Districts] CHECK CONSTRAINT [FK_dbo.Districts_dbo.States_StateId]

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_dbo.States_dbo.Countries_CountryId]') AND parent_object_id = OBJECT_ID(N'[dbo].[States]'))
ALTER TABLE [dbo].[States]  WITH CHECK ADD  CONSTRAINT [FK_dbo.States_dbo.Countries_CountryId] FOREIGN KEY([CountryId])
REFERENCES [dbo].[Countries] ([CountryId])
ON DELETE CASCADE

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_dbo.States_dbo.Countries_CountryId]') AND parent_object_id = OBJECT_ID(N'[dbo].[States]'))
ALTER TABLE [dbo].[States] CHECK CONSTRAINT [FK_dbo.States_dbo.Countries_CountryId]

IF NOT EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_dbo.SubDistricts_dbo.Districts_DistrictId]') AND parent_object_id = OBJECT_ID(N'[dbo].[SubDistricts]'))
ALTER TABLE [dbo].[SubDistricts]  WITH CHECK ADD  CONSTRAINT [FK_dbo.SubDistricts_dbo.Districts_DistrictId] FOREIGN KEY([DistrictId])
REFERENCES [dbo].[Districts] ([DistrictId])
ON DELETE CASCADE

IF  EXISTS (SELECT * FROM sys.foreign_keys WHERE object_id = OBJECT_ID(N'[dbo].[FK_dbo.SubDistricts_dbo.Districts_DistrictId]') AND parent_object_id = OBJECT_ID(N'[dbo].[SubDistricts]'))
ALTER TABLE [dbo].[SubDistricts] CHECK CONSTRAINT [FK_dbo.SubDistricts_dbo.Districts_DistrictId]

