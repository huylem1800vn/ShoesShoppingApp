-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: May 07, 2022 at 09:33 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_shoesshopping`
--

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `image` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id`, `name`, `description`, `image`) VALUES
(1, 'Nike', 'As one of the world’s largest shoe shoe brands, Nike has always been at the leading edge of innovation, technology development, and cutting-edge marketing campaigns that help it surpass other brands in terms of popularity and sales. Nike’s ‘Just do It’ marketing campaign featuring Colin Kaepernick has proven to be beneficial for the company as it helped grow their online sales of footwear products across the world. Nike, the world’s largest sports footwear brand, is reinventing itself for the digital era. The company is taking several steps toward its goal of becoming a retail tech company and some of these tactics already appear to be working.  Nike is also getting its flagship products right with shoes like Nike Air Max 270, ZoomX, React, and VaporMax.', 'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTNoD9oT_VnEYNKKeOor8U4qK5T1LF4bC2iRDD75fQdveQMHTUA'),
(2, 'adidas', 'With its roots in Germany, adidas has become one of the top shoe brands in the world. The company produces more than 900 million sports and lifestyle products with independent manufacturing partners around the world. It generated sales of approximately $25 billion in 2019, and these staggering numbers are a testament to what a large and multifaceted company adidas has become. With more than 1000 stores across the world, the shoe brand has risen to extreme heights of success. Adidas has not only become a symbol of sports fashion but has also presented a collective image of street, pop-culture, music, sports, and other fashion statements, defining its existence through its remarkable communication strategies.', 'https://scontent.fsgn12-1.fna.fbcdn.net/v/t1.6435-9/180681664_4068000156572060_2031692734274211791_n.png?_nc_cat=110&ccb=1-6&_nc_sid=09cbfe&_nc_ohc=RlYdeiTCDCwAX_k3vfy&_nc_ht=scontent.fsgn12-1.fna&oh=00_AT_d6sBOkJ4_KWrTfw8EW_WdYZ9-qwdIsxvH2BEOPR83kA&oe=629C6492'),
(3, 'New Balance', 'Unlike many of its rivals, the Boston-based multinational corporation New Balance shuns celebrity endorsement. The company claims to emphasize substance over style by highlighting lesser-known athletes. Besides manufacturing men’s and women’s shoes for basketball, tennis, golf, hiking, running, and cross training, New Balance offers fitness apparel and shoes for kids and owns shoemaker Dunham. The company’s product portfolio also includes Aravon shoes for comfort performance, Warrior hockey gear, PF Flyers footwear, and Brine, a leader in field hockey, lacrosse, soccer, and volleyball. The company is striving to expand beyond the US and it recently opened its first UK store on London’s Oxford Street.', 'https://logowik.com/content/uploads/images/new-balance.jpg'),
(4, 'ASICS', 'ASICS is one of Japan’s top shoe companies and purveyor of other equipment. Since its foundation in 1949, ASICS has been a global footwear brand committed to nurturing youth around the world through sports. Over more than six decades, the company has provided its own products while creating changes in the social environment. ASICS recently announced the launch of its new product ‘Solution Speed’ tennis footwear, which is designed to enhance agility and speed during the game. The Solution Speed shoe features three of company’s advanced technologies: Court Specific FlyteFoam, TWISTRUSS, and FLEXION FIT.', 'https://logowik.com/content/uploads/images/asics2623.jpg'),
(5, 'Skechers', 'Founded in 1992, Skechers is one of the fastest growing shoe companies in the US, focusing on trendy and casual styles for men and women between the ages of 19 and 40. The billion-dollar, award-winning company’s success stems from its high-quality product offerings, cutting-edge print and television advertising, and diversified domestic and global distribution channels. Skechers’ products include boots, sneakers, training shoes, oxfords, sandals, and semi-dressy shoes. The company offers street-focused and fashion footwear under the Zoo York, Marc Ecko, and Mark Nason brands. Skechers shoes are sold in more than 160 countries through specialty stores and some 390 company-owned outlet stores.', 'http://sh.skechers.com/logos/images/CORP_SKX_WEB-logo.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` varchar(50) NOT NULL,
  `customer_name` text NOT NULL,
  `customer_address` text NOT NULL,
  `customer_phone` text NOT NULL,
  `total_amount` double NOT NULL,
  `order_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `order_detail`
--

CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL,
  `order_id` varchar(50) NOT NULL,
  `product_id` int(11) NOT NULL,
  `size` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_amount` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `price` double NOT NULL,
  `promotional_price` double DEFAULT NULL,
  `description` text NOT NULL,
  `per_red` int(11) NOT NULL,
  `image_1` text DEFAULT NULL,
  `image_2` text DEFAULT NULL,
  `image_3` text DEFAULT NULL,
  `brand_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `promotional_price`, `description`, `per_red`, `image_1`, `image_2`, `image_3`, `brand_id`) VALUES
(1, 'Nike Air Force 1 \'07', 2649000, 2119200, 'LEGENDARY STYLE REFINED.\r\n\r\n\r\nThe radiance lives on in the Nike Air Force 1 \'07, the b-ball OG that puts a fresh spin on what you know best: durably stitched overlays, clean finishes and the perfect amount of flash to make you shine.\r\n\r\n\r\nBenefits\r\n\r\nThe stitched overlays on the upper add heritage style, durability and support.\r\nOriginally designed for performance hoops, the Nike Air cushioning adds lightweight, all-day comfort.\r\nThe low-cut silhouette adds a clean, streamlined look.\r\nThe padded collar feels soft and comfortable.\r\n\r\nProduct Details\r\n\r\nFoam midsole\r\nPerforations on the toe\r\nRubber sole\r\nColour Shown: White/White\r\nStyle: CW2288-111\r\nCountry/Region of Origin: Vietnam,India\r\n\r\nAir Force 1 Origins\r\n\r\nDebuting in 1982, the AF-1 was the first basketball shoe to house Nike Air, revolutionising the game while rapidly gaining traction around the world. Today, the Air Force 1 stays true to its roots with the same soft and springy cushioning that changed sneaker history.\r\n\r\n', 20, 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/b7d9211c-26e7-431a-ac24-b0540fb3c00f/air-force-1-07-shoe-WrLlWX.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/00375837-849f-4f17-ba24-d201d27be49b/air-force-1-07-shoe-WrLlWX.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/3cc96f43-47b6-43cb-951d-d8f73bb2f912/air-force-1-07-shoe-WrLlWX.png', 1),
(2, 'Nike Dunk High Retro SE', 3519000, 2463300, 'GET CROSSED WITH OLD-SCHOOL HOOPS.\r\n\r\n\r\nGo full throttle in the Nike Dunk Low High Retro.It brings motocross-inspired details like bold branding, reflective accents and fresh materials to the wardrobe favourite.Crisp leather, bright panels and flashy details deliver high-octane energy.\r\n\r\n\r\nBenefits\r\n\r\nReflective details, including accents in the laces, for the perfect amount of flash.\r\nBright panels and bold branding (including embroidered \"NIKE\" on the side) celebrate motocross fashion.\r\nCrisp leather upper has a slight sheen and ages to soft perfection.\r\nFoam midsole offers lightweight, responsive cushioning.\r\nRubber outsole with classic hoops pivot circle adds traction.\r\n\r\nProduct Details\r\n\r\nPadded, low-cut collar\r\nFoam insole\r\nPerforations on the toe\r\nRubber outsole\r\nNot intended for use as Personal Protective Equipment (PPE)\r\nColour Shown: Black/Black/White/Hyper Royal\r\nStyle: DD3359-001\r\nCountry/Region of Origin: Vietnam\r\n\r\nDunk Origins\r\n\r\nOriginally a classic Nike hoops shoe, the Dunk was organically adopted by skate culture—and in time re-engineered for Nike SB.These days, the SB Dunk doubles as a starting point for many of the brand\'s most influential design collaborators, from small-town skate shops to iconic New York fashion h', 30, 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/2a617295-4b1a-46e1-91e2-6963fd252c59/dunk-high-retro-se-shoes-tXRLdK.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/1af80254-b653-42f8-8702-61b39feca583/dunk-high-retro-se-shoes-tXRLdK.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/433f980c-d56b-43d2-8475-fa917e4f2a7b/dunk-high-retro-se-shoes-tXRLdK.png', 1),
(3, 'Nike Air Max Pre-Day', 3829000, 2680300, 'THE DAWN OF NEW AIR.\r\n\r\n\r\nTaking the classic look of heritage Nike Running into a new realm, the Nike Air Max Pre-Day brings you a fast-paced look that\'s ready for today\'s world.A true nod to the past with a design made from at least 20% recycled material by weight, it keeps the retro-athletics aesthetic alive.A new Air window energises the look, mixing head-turning style with unbelievably soft cushioning.\r\n\r\n\r\nBenefits\r\n\r\nThe design creates a sleek look that exposes the Air unit in a completely fresh way.\r\nOriginally designed for performance running, the Air cushioning adds unbelievable comfort.\r\nThe updated rubber Waffle sole adds heritage styling, traction and durability.\r\nInspired by heritage running shoes, the upper features a large retro Swoosh, stitched overlays with throwback looks and a mixture of materials.\r\n\r\nProduct Details\r\n\r\nRubber sole\r\n100% recycled polyester laces\r\nFoam midsole\r\nColour Shown: Black/Anthracite/White\r\nStyle: DC9402-001\r\nCountry/Region of Origin: Vietnam\r\n\r\nNike Air Max Origins\r\n\r\nRevolutionary Air technology first made its way into Nike footwear in 1978.In 1987, the Air Max 1 debuted with visible Air technology in its heel, allowing fans more than just the feel of Air cushioning—suddenly they could see it.Since then, next-generation Air Max shoes have become a hit with athletes and collectors by offering striking colour combinations and reliable, lightweight cushioning.', 30, 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/ba30b216-3e6f-4322-af0e-8dae19dcf8df/air-max-pre-day-shoes-jMh2rB.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/5e142e54-f8c0-4468-9d86-fa8a5a18457a/air-max-pre-day-shoes-jMh2rB.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/0e28b30a-47b8-4eab-b4bd-122f94346097/air-max-pre-day-shoes-jMh2rB.png', 1),
(4, 'Nike Air Zoom Pegasus 38', 3829000, 3063200, 'Your workhorse with wings returns. The Nike Air Zoom Pegasus 38 continues to put a spring in your step, using the same responsive foam as its predecessor. Breathable mesh in the upper combines the comfort and durability you want with a wider fit at the toes.\r\n\r\n\r\nSomething Old, Something New\r\n\r\nA wider forefoot means more space for your toes, while the shoe\'s fit maintains the comfortable feel you expect from the Pegasus. Mesh in the upper feels breathable and plush while stretching with your foot for a comfortable shape and fit.\r\n\r\n\r\nSpring with Your Step\r\n\r\nNike React foam is lightweight, springy and durable. More foam means better cushioning without the bulk. A Zoom Air unit puts more bounce with every step. It\'s closer to your foot for responsiveness.\r\n\r\n\r\nSecure Fit\r\n\r\nMidfoot webbing gives a snug fit when you tighten the laces.\r\n\r\n\r\nMore Benefits\r\n\r\nMore foam in the tongue helps cushion the top of your foot.\r\nColour Shown: Black/Green Strike/Total Orange/Volt\r\nStyle: DQ4994-010\r\nCountry/Region of Origin: China', 20, 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/a50a6236-83d6-46bc-865a-b93293922226/air-zoom-pegasus-38-road-running-shoes-4PMfGR.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/825a7413-89d4-4100-9584-65d42d3331ff/air-zoom-pegasus-38-road-running-shoes-4PMfGR.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/daa1339b-041c-4069-b0ab-aeb72491856c/air-zoom-pegasus-38-road-running-shoes-4PMfGR.png', 1),
(5, 'Nike Air Max 97 OG', 5279000, 3695300, 'Push your style full speed ahead with the Nike Air Max 97 OG. Its iconic design takes inspiration from water droplets and Japanese bullet trains. Full-length Nike Air cushioning lets you ride in first-class comfort.\r\n\r\n\r\nBenefits\r\n\r\nOriginally designed for performance running, Nike Air cushioning (the first of its kind) pairs best-in-class comfort with sporty style.\r\nHidden lacing system creates a streamlined look.\r\nDurable, synthetic upper keeps the organic look of the original while mesh underlays help keep it airy.\r\nRubber outsole adds traction and durability.\r\n\r\nProduct Details\r\n\r\nFoam sockliner and midsole\r\nPull tabs\r\nColour Shown: Atlantic Blue Heather/Metallic Silver/Black/Voltage Yellow\r\nStyle: DM0028-400\r\nCountry/Region of Origin: Indonesia\r\n\r\nNike Air Max Origins\r\n\r\nRevolutionary Air technology first made its way into Nike footwear in 1978. The Air Max 1 debuted with visible Air technology in its heel, allowing fans more than just the feel of Air cushioning—suddenly they could see it. Since then, next-generation Air Max shoes have become a hit with athletes and collectors by offering striking colour combinations and reliable, lightweight cushioning.', 30, 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5/d9e6fed1-02b8-4bdf-8fe4-5becdc0d3702/air-max-97-og-shoes-pKxPsp.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/8734cb7d-ffec-484f-afa4-9d16c8b4679b/air-max-97-og-shoes-pKxPsp.png', 'https://static.nike.com/a/images/t_PDP_864_v1/f_auto,b_rgb:f5f5f5,q_80/8ded1467-dba8-4027-b6f4-fef9ff1df3ad/air-max-97-og-shoes-pKxPsp.png', 1),
(6, 'ULTRABOOST 20 EXPLORER SHOES', 5000000, 2500000, 'ULTRABOOST 20 EXPLORER SHOES\r\nRESPONSIVE RUNNING SHOES FOR ALL-DAY COMFORT.\r\nEven with its innovative design and legendary technology, the vision behind Ultraboost is quite simple — comfort. Lace into these adidas running shoes and find it wherever the day takes you. Boost cushioning fuels every step with energy, and the water-repellent upper keeps you going in cool weather.\r\nSPECIFICATIONS\r\nRegular fit\r\nLace closure\r\nTextile lining\r\nBoost midsole\r\nContinental™ Rubber outsole\r\nColor: Core Black / Pink Tint / Cloud White\r\nProduct code: GY8107', 50, 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/4f3ad187b10341d289c6ad1000e46b82_9366/Ultraboost_20_Explorer_Shoes_White_GY8108_01_standard.jpg', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/dfa174a5f1aa4ade8d56ad1000e477b8_9366/Ultraboost_20_Explorer_Shoes_White_GY8108_02_standard.jpg', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/f9c6488fec84499ea56ead1000e4850a_9366/Ultraboost_20_Explorer_Shoes_White_GY8108_04_standard.jpg', 2),
(7, 'X SPEEDFLOW.4 TURF BOOTS', 1500000, 1050000, 'X SPEEDFLOW.4 TURF BOOTS\r\nA LIGHTWEIGHT BOOT FOR YOUR VERSION OF SPEED.\r\nOn the pitch, speed is a virtue. On the court, it\'s the only thing that counts. Think fast. Move faster. Find your flow and leave the rest behind. Take everything in your stride in these adidas X football boots. Their soft, coated textile upper offers a comfortable ride as you explode into action. Underneath, a lugged rubber outsole keeps you quick on artificial turf.\r\nSPECIFICATIONS\r\nLace closure\r\nCoated textile upper\r\nRubber outsole for artificial turf\r\nColor: Cloud White / Iron Metallic / Solar Red\r\nProduct code: FY3335', 30, 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/446f6eaa69a64954a729ad0c00a9c32a_9366/X_Speedflow.4_Turf_Boots_White_FY3335_01_standard.jpg', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3cdbf2ce743944f19fc0ad0c00a9d08b_9366/X_Speedflow.4_Turf_Boots_White_FY3335_02_standard_hover.jpg', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/e3d74d0d8480464e9482ad0c00a9de0a_9366/X_Speedflow.4_Turf_Boots_White_FY3335_04_standard.jpg', 2),
(8, 'SUPERSTAR SHOES', 2500000, 1250000, 'SUPERSTAR SHOES\r\nHALLOWEEN-INSPIRED SHOES WITH THE ICONIC SHELL TOE.\r\nAn icon since they first stepped onto the hardwood in the 1970s, adidas Superstar Shoes have never stopped pushing boundaries and breaking rules. Which makes them the perfect choice for haunted houses, horror movie marathons or whatever spooky Halloween plans you\'re cooking up. This version features seasonal colours and details, including a lace clip inspired by vampire teeth, to keep the creepy vibes going.\r\nSPECIFICATIONS\r\nLace closure\r\nLeather upper\r\nTextile lining\r\nMoulded sockliner\r\nRubber outsole\r\nColor: Core Black / Cherry Red / Glory Mint\r\nProduct code: GW8843', 50, 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/051824afe6ba4809a195ad59011dceac_9366/Superstar_Shoes_Black_GW8843_01_standard.jpg', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/d6b3af1e3e724c18aa82ad59011dddc2_9366/Superstar_Shoes_Black_GW8843_02_standard_hover.jpg', 'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/99976cf33ef4484ebae6ad59011e0153_9366/Superstar_Shoes_Black_GW8843_42_detail.jpg', 2),
(9, 'Fresh Foam X 1080v12', 3672000, 1836000, 'This model runs large, compared to previous versions. You may consider ordering down from your normal size.\r\nIf we only made one running shoe, that shoe would be the 1080. What makes the 1080 unique isn’t just that it’s the best running shoe we make, it’s also the most versatile. The 1080 delivers top-of-the-line performance to every kind of runner, whether you’re training for world-class competition, or catching a rush hour train. The Fresh Foam X 1080v12 represents a consistent progression of the model’s signature qualities. The smooth transitions of the pinnacle underfoot cushioning experience are fine-tuned with updated midsole mapping, which applies more foam to wider areas of the midsole and increases flexibility at the narrower points. The ultra-modern outlook is also reflected in the 1080’s upper construction. The v12 offers a supportive, second-skin style fit with an engineered Hypoknit upper, for a more streamlined overall design.\r\n Meets New Balance\'s green leaf standard\r\nMeets New Balance’s green leaf standard: upper is made of 50% or more recycled content, and at least one midsole/outsole material contains a minimum of 3% bio-based or 5% recycled content. Learn more about New Balance’s green leaf standard here.', 50, 'https://nb.scene7.com/is/image/NB/m1080m12_nb_02_i?$pdpflexf2MD2x$&fmt=webp&wid=1026&hei=1026', 'https://nb.scene7.com/is/image/NB/m1080m12_nb_05_i?$pdpflexf2MD2x$&fmt=webp&wid=1026&hei=1026', 'https://nb.scene7.com/is/image/NB/m1080m12_nb_03_i?$dw_detail_main_lg$&bgc=f1f1f1&layer=1&bgcolor=f1f1f1&blendMode=mult&scale=10&wid=1600&hei=1600', 3),
(10, 'Fresh Foam X 880v12', 3098800, 1549400, 'When it comes to running, going the extra mile isn’t just a figure of speech. Whether it’s morning exercise, intense training, or head-clearing relaxation, a daily regimen of training miles, combined with ordinary movement throughout the day, demands a lot from a running shoe. With running comfortably for longer in mind, the 880 was built to provide consistent performance for the neutral runner.  The Fresh Foam X 880v12 is modernization that can be seen and felt. A soft foam compound, and dual-layer midsole setup is featured alongside a sleek, jacquard mesh upper featuring strategic zones of support and breathability.\r\nWith premium cushioning and a durable build, the new Fresh Foam X 880v12 is our hardest working shoe for the daily runner.\r\nEnhanced softness\r\nA new dual layer midsole featuring top-bed foam cushioning and underfoot Fresh Foam X makes for a soft step in and heel strike\r\nLight & supportive fit\r\nA plush yet breathable double-knit engineered mesh upper provides strategic zones of support while maintaining flexibility', 50, 'https://nb.scene7.com/is/image/NB/m880m12_nb_02_i?$pdpflexf2MD2x$&fmt=webp&wid=1026&hei=1026', 'https://nb.scene7.com/is/image/NB/m880m12_nb_05_i?$pdpflexf2MD2x$&fmt=webp&wid=1026&hei=1026', 'https://www.newbalance.com/pd/fresh-foam-x-880v12/M880V12-37428.html', 3),
(11, 'New Balance x Aimé Leon Dore 650', 4000000, 2000000, 'After reworking and bringing new life to the classic, off-court P550 Basketball Oxford, New Balance and Aimé Leon Dore turn their attention to the high top. Taking inspiration from the 550, the 650 adapts the outsized proportions of the original, on-court model, while featuring a puff and stitch collar and a raw-edged leather eyerow, makes for the total basketball shoe.\r\nStyle #: BB650RA1\r\n  0 grams (0 oz)\r\nLeather upper\r\nPuff and stitch collar\r\nRaw edged leather eyerow\r\nCustom, co-branded graphics at tongue label and heel\r\nCotton laces\r\nDesigned in collaboration with Aimé Leon Dore\r\nThis item is limited to \"1\" per order\r\nItem cannot be returned.\r\nAimé Leon Dore reimagines the high top, on-court classic 650. Adapting the oversized proportions of the original, the silhouette’s bold aesthetic remains while upgraded accents are added over four colorways.\r\nVisible craftsmanship\r\nThe use of raw-edged leather eyerows requires precise alignment and stitching', 50, 'https://nb.scene7.com/is/image/NB/bb650ra1_nb_02_i?$pdpflexf2MD2x$&fmt=webp&wid=1026&hei=1026', 'https://nb.scene7.com/is/image/NB/bb650ra1_nb_05_i?$pdpflexf2MD2x$&fmt=webp&wid=1026&hei=1026', 'https://nb.scene7.com/is/image/NB/bb650ra1_nb_03_i?$dw_detail_main_lg$&bgc=f1f1f1&layer=1&bgcolor=f1f1f1&blendMode=mult&scale=10&wid=1600&hei=1600', 3),
(12, 'GEL-CUMULUS 24', 4000000, 2000000, 'The GEL-CUMULUS™ 24 shoe is a versatile everyday trainer for various runners covering different distances. From the upper to the foam underfoot, this shoe feels softer and more responsive.\r\n\r\nTo improve comfort, the upper features an improved heel fit. Meanwhile, the engineered mesh improves breathability to keep your feet cool throughout your run.\r\n\r\nThis smooth distance trainer is also updated with our FF BLAST™ cushioning. It keeps the shoe lightweight while creating a softer touchdown and a more energized toe-off.', 50, 'https://images.asics.com/is/image/asics/1011B366_003_SR_RT_GLB?$sfcc-product$', 'https://images.asics.com/is/image/asics/1011B366_003_SB_FR_GLB?$sfcc-product$', 'https://images.asics.com/is/image/asics/1011B366_003_SB_BT_GLB?$sfcc-product$', 4),
(13, 'GEL-CUMULUS 24', 5000000, 2500000, 'The GEL-CUMULUS™ 24 shoe is a versatile everyday trainer for various runners covering different distances. From the upper to the foam underfoot, this shoe feels softer and more responsive.\r\n\r\nTo improve comfort, the upper features an improved heel fit. Meanwhile, the engineered mesh improves breathability to keep your feet cool throughout your run.\r\n\r\nThis smooth distance trainer is also updated with our FF BLAST™ cushioning. It keeps the shoe lightweight while creating a softer touchdown and a more energized toe-off.', 50, 'https://images.asics.com/is/image/asics/1011B366_001_SR_RT_GLB?$sfcc-product$', 'https://images.asics.com/is/image/asics/1011B366_001_SB_FL_GLB?$sfcc-product$', 'https://images.asics.com/is/image/asics/1011B366_001_SB_BK_GLB?$sfcc-product$', 4),
(14, 'NOVABLAST 2 SPS', 3000000, 1500000, 'The NOVABLAST™ 2 running shoe has been reworked with modern, street-style aesthetics. It\'s designed to keep your step soft and your mind ready for the next move.\r\n\r\nThis lightweight design also includes our FF BLAST™ cushioning. It\'s been re-tuned with lifestyle functionality and comfort.\r\n\r\nFormed with a no-sew upper, this shoe also features a redesigned heel that offers a more accommodating fit.', 50, 'https://images.asics.com/is/image/asics/1201A483_001_SR_RT_GLB?$sfcc-product$', 'https://images.asics.com/is/image/asics/1201A483_001_SB_FL_GLB?$sfcc-product$', 'https://images.asics.com/is/image/asics/1201A483_001_SB_BK_GLB?$sfcc-product$', 4),
(15, 'Sport Mens Track - 232001-NVOR', 3400000, 1700000, 'Keep your eyes on the sporty style and cushioned comfort with the SKECHERS Track shoe. Smooth action leather and mesh fabric upper in a lace up athletic training sneaker with stitching and overlay accents. Memory Foam insole.\r\n\r\nFeatures:\r\n- Smooth action leather upper\r\n- Mesh fabric panels for cooling effect\r\n- Lace up athletic sporty training sneaker\r\n- Stitching accents\r\n- Leather and synthetic overlays at toe, sides and heel for stability\r\n- Side S logo\r\n- Sporty mesh with lightly textured finish\r\n- Lace up front\r\n- Padded collar and tongue\r\n- Soft fabric shoe lining\r\n- Memory Foam full length cushioned comfort insole\r\n- Shock absorbing lightweight flexible outsole\r\n- Highly flexible traction outsole\r\n- 1 inch built in heel', 50, 'https://cdn.shopify.com/s/files/1/0488/4001/6033/products/232001_NVOR_53b02557-7196-4540-882b-22904aac2dc1_1024x1024.jpg?v=1644220616', 'https://cdn.shopify.com/s/files/1/0488/4001/6033/products/232001_NVOR_C_1024x1024.jpg?v=1644220616', 'https://cdn.shopify.com/s/files/1/0488/4001/6033/products/232001_NVOR_B_1024x1024.jpg?v=1644220616', 5),
(16, 'GOWalk 6 - 216204-CHAR', 3000000, 1500000, 'Elevate your steps in dynamic comfort with the Skechers GOwalk 6™ shoe. This slip-on sneaker features an athletic mesh upper, lightweight ULTRA GO® Technology cushioning midsole, Air-Cooled Goga Mat™ Technology insole, and high-rebound Hyper Pillar Technology™ for added support.\r\n\r\nFeatures:\r\n- Air-Cooled Goga Mat™ Technology breathable insole with high-rebound cushioning\r\n- Lightweight, responsive ULTRA GO® Technology cushioning\r\n- High-rebound ultra-lightweight Hyper Pillar Technology™ for added support\r\n- Ortholite® comfort foam insole layer adds long-term cushioning and high-level breathability with 5% recycled rubber content\r\n- Athletic mesh upper with a stretch-laced front\r\n- Slip-on style comfort walking sneaker design\r\n- Durable dual-density traction outsole for stability\r\n- Machine washable\r\n- 1 1/2 inch heel\r\n- Skechers® logo detail', 50, 'https://cdn.shopify.com/s/files/1/0488/4001/6033/products/216204_CHAR_ca9de536-ff56-43ad-9992-c559b27c2fda_1024x1024.jpg?v=1644219604', 'https://cdn.shopify.com/s/files/1/0488/4001/6033/products/216204_CHAR_B_060ae2b4-221c-4f11-98fa-88d31bd98ec7_1024x1024.jpg?v=1644219604', 'https://cdn.shopify.com/s/files/1/0488/4001/6033/products/216204_CHAR_D_8d5851a5-51b6-49bd-8add-9441a1296de3_1024x1024.jpg?v=1644219604', 5),
(17, 'GOWalk 6 - 216202-NVOR', 1890000, 1512000, 'Get next level comfort on your walks with the Skechers GOwalk 6™ shoe. This slip-on sneaker features a Stretch Fit® Technology engineered mesh upper, lightweight ULTRA GO® Technolofy cushioning midsole, Air-Cooled Goga Mat™ Technolofy insole, and high-rebound Hyper Pillar Technology™ for added support.\r\n\r\nFeatures:\r\n- Air-Cooled Goga Mat™ Technology breathable insole with high-rebound cushioning\r\n- Lightweight, responsive ULTRA GO®Technology cushioning\r\n- High-rebound ultra-lightweight Hyper Pillar Technology™ for added support\r\n- Stretch Fit® Technolofy slip-on design for sock-like comfort\r\n- Ortholite® comfort foam insole layer adds long-term cushioning and high-level breathability with 5% recycled rubber - content\r\n- Athletic engineered mesh upper\r\n- Slip-on style comfort walking sneaker design\r\n- Durable dual-density traction outsole for stability\r\n- 1 1/2 inch heel\r\n- Skechers® logo detail', 25, 'https://cdn.shopify.com/s/files/1/0488/4001/6033/products/216202_NVOR_a15ba789-5a98-4940-8372-29bbb86bf9e3_1024x1024.jpg?v=1644219564', 'https://cdn.shopify.com/s/files/1/0488/4001/6033/products/216202_NVOR_B_69b1e1c8-dc9a-4173-a311-c969a5478fcc_1024x1024.jpg?v=1644219564', 'https://cdn.shopify.com/s/files/1/0488/4001/6033/products/216202_NVOR_C_5bddfad4-1df6-4c8c-b035-03198a224907_1024x1024.jpg?v=1644219565', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `brand_id` (`brand_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `order_detail`
--
ALTER TABLE `order_detail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order_detail`
--
ALTER TABLE `order_detail`
  ADD CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  ADD CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
