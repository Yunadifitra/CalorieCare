-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 14, 2023 at 05:41 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `capstone`
--

-- --------------------------------------------------------

--
-- Table structure for table `consumption`
--

CREATE TABLE `consumption` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL DEFAULT current_timestamp(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `consumption`
--

INSERT INTO `consumption` (`id`, `user_id`, `food_id`, `created_at`) VALUES
(4, 1, 23, '2023-12-14 22:59:15.238229'),
(5, 1, 23, '2023-12-14 23:23:50.286571'),
(6, 1, 12, '2023-12-14 23:23:53.492278'),
(7, 1, 1, '2023-12-14 23:23:56.794015');

-- --------------------------------------------------------

--
-- Table structure for table `diet`
--

CREATE TABLE `diet` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `calories` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` int(11) NOT NULL,
  `calories` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `calories`, `name`) VALUES
(1, 2209, 'Chicken Marsala'),
(2, 1887, 'Goulash'),
(3, 1471, 'Burger King Triple Whopper'),
(4, 994, 'Burger King Double Whopper with Cheese'),
(5, 963, 'Veggie Patty'),
(6, 897, 'Flaxseed'),
(7, 894, 'Burger King Double Whopper'),
(8, 885, 'Durian'),
(9, 841, 'Calzone'),
(10, 839, 'Wendyâ€™s Baconator'),
(11, 832, 'Freekeh'),
(12, 818, 'Sunflower Seeds'),
(13, 774, 'Kebab'),
(14, 759, 'Burger King Whopper with Cheese'),
(15, 757, 'Brown Rice'),
(16, 740, 'Burger King Angry Whopper'),
(17, 721, 'Meatloaf'),
(18, 716, 'Amaranth'),
(19, 714, 'Beef Pizza'),
(20, 700, 'Wendyâ€™s Son of Baconator'),
(21, 699, 'Mac and Cheese'),
(22, 699, 'Macaroni and Cheese'),
(23, 673, 'Chicken Pot Pie'),
(24, 672, 'Burger King Whopper'),
(25, 672, 'Whopper'),
(26, 670, 'Brown Rice'),
(27, 662, 'Fried Rice'),
(28, 659, 'Burger King Original Chicken Sandwich'),
(29, 650, 'Couscous'),
(30, 641, 'Reuben Sandwich'),
(31, 641, 'Arbyâ€™s Reuben'),
(32, 632, 'Cobb Salad'),
(33, 627, 'Kamut'),
(34, 626, 'Quinoa'),
(35, 611, 'Mushroom Pizza'),
(36, 601, 'Spelt Semolina'),
(37, 601, 'Wheat Semolina'),
(38, 600, 'Buffalo Chicken Pizza'),
(39, 593, 'BLT'),
(40, 591, 'Burger King Premium Alaskan Fish Sandwich'),
(41, 588, 'Spelt'),
(42, 585, 'Fish and Chips'),
(43, 585, 'Chop Suey'),
(44, 585, 'Fish and Chips'),
(45, 583, 'Buckwheat'),
(46, 575, 'Nachos with Cheese'),
(47, 571, 'White Pizza'),
(48, 567, 'Buckwheat Groats'),
(49, 562, 'Corn'),
(50, 561, 'McDonaldâ€™s Big Mac'),
(51, 556, 'Barley'),
(52, 553, 'Pearl Barley'),
(53, 551, 'Pulled Pork Sandwich'),
(54, 549, 'Polenta'),
(55, 539, 'Bacon and Eggs'),
(56, 523, 'Cottage Pie'),
(57, 521, 'Millet Flour'),
(58, 517, 'Big Nâ€™ Tasty'),
(59, 517, 'Zinger'),
(60, 517, 'Zinger Burger'),
(61, 511, 'Poutine'),
(62, 491, 'Cellophane Noodles'),
(63, 490, 'Butter Chicken'),
(64, 489, 'Arbyâ€™s Grand Turkey Club'),
(65, 488, 'Cornstarch'),
(66, 484, 'Biryani'),
(67, 484, 'Millet'),
(68, 481, 'Meatball Sandwich'),
(69, 480, 'Spicy Italian'),
(70, 467, 'Tortilla'),
(71, 460, 'Bianca Pizza'),
(72, 453, 'Pineapple'),
(73, 453, 'Pineapple'),
(74, 451, 'Chicken Pizziola'),
(75, 451, 'McDonaldâ€™s McRib'),
(76, 451, 'McRib'),
(77, 449, 'Carvel'),
(78, 442, 'Cornmeal'),
(79, 438, 'Corn Dog'),
(80, 437, 'McDonaldâ€™s Double Cheeseburger'),
(81, 435, 'Hummus'),
(82, 434, 'Raisins'),
(83, 432, 'Wheat Germ'),
(84, 431, 'Fish Sandwich'),
(85, 423, 'Chicken Fried Steak'),
(86, 422, 'Rye Bran'),
(87, 422, 'Macaroni'),
(88, 420, 'Orange Chicken'),
(89, 420, 'Wendyâ€™s Jr. Bacon Cheeseburger'),
(90, 418, 'Chimichanga'),
(91, 414, 'Double Cheeseburger'),
(92, 413, 'Soursop Fruit'),
(93, 410, 'Cheeseburger'),
(94, 410, 'Chicken Sandwich'),
(95, 410, 'Italian BMT'),
(96, 407, 'Whole Grain Wheat'),
(97, 403, 'McDonaldâ€™s McDouble'),
(98, 401, 'Peking Duck'),
(99, 400, 'Smarties McFlurry'),
(100, 400, 'Filet-o-Fish'),
(101, 399, 'Vegetable Pizza'),
(102, 399, 'Vegetarian Pizza'),
(103, 398, 'Curly Fries'),
(104, 397, 'Sloppy Joes'),
(105, 392, 'Chicken Caesar Salad'),
(106, 392, 'Grilled Cheese Sandwich'),
(107, 391, 'McDonaldâ€™s Filet-o-Fish'),
(108, 389, 'Veggie Burger'),
(109, 386, 'Wheat Starch'),
(110, 381, 'Dippin Dots'),
(111, 380, 'Ramen'),
(112, 380, 'Pizza Hut Stuffed Crust Pizza'),
(113, 380, 'Stuffed Crust Pizza'),
(114, 380, 'Bean Burrito'),
(115, 375, 'Pad Thai'),
(116, 374, 'Spaghetti Bolognese'),
(117, 371, 'Instant Ramen'),
(118, 371, 'Ramen'),
(119, 367, 'Chicken Teriyaki Sandwich'),
(120, 361, 'Sausage Roll'),
(121, 360, 'Baby Back Ribs'),
(122, 360, 'BBQ Ribs'),
(123, 360, 'Drumsticks'),
(124, 360, 'Arbyâ€™s Roast Beef Classic'),
(125, 360, 'Arbyâ€™s Roast Beef Max'),
(126, 359, 'McDonaldâ€™s McChicken'),
(127, 356, 'Dominos Philly Cheese Steak Pizza'),
(128, 352, 'Ham and Cheese Sandwich'),
(129, 352, 'Ham Sandwich'),
(130, 350, 'Spring Roll'),
(131, 350, 'Spring Rolls'),
(132, 349, 'Corned Beef Hash'),
(133, 346, 'Burger King Whopper Jr.'),
(134, 340, 'Dairy Milk McFlurry'),
(135, 340, 'McFlurry Oreo'),
(136, 335, 'Bean Stew'),
(137, 333, 'Hot Fudge Sundae'),
(138, 330, 'Quattro Formaggi Pizza'),
(139, 329, 'Cold Stone Creamery'),
(140, 329, 'Cheese Tortellini'),
(141, 329, 'Tortellini'),
(142, 327, 'Magnum Double Chocolate'),
(143, 326, 'Burrito'),
(144, 326, 'Chicken Fajita'),
(145, 323, 'Black Rice'),
(146, 323, 'Enchiladas'),
(147, 321, 'Sicilian Pizza'),
(148, 320, 'Avocado'),
(149, 320, 'Spinach Tortellini'),
(150, 320, 'Avocado'),
(151, 318, 'Crunchie McFlurry'),
(152, 318, 'Tuna Pizza'),
(153, 314, 'BBQ Pizza'),
(154, 313, 'Deep Dish Pizza'),
(155, 312, 'Hot Dog'),
(156, 312, 'Subway Club Sandwich'),
(157, 309, 'BBQ Chicken Pizza'),
(158, 309, 'Chicken Pizza'),
(159, 305, 'Magnum Double Caramel'),
(160, 305, 'Pizza Hut Supreme Pizza'),
(161, 304, 'Dal'),
(162, 300, 'Philly Cheese Steak'),
(163, 300, 'Wholegrain Oat'),
(164, 300, 'Shells'),
(165, 300, 'McDonaldâ€™s Cheeseburger'),
(166, 295, 'Pork Chop'),
(167, 290, 'Fajita'),
(168, 290, 'Meat Pie'),
(169, 290, 'Magnum Gold'),
(170, 290, 'Rocky Road Ice Cream'),
(171, 290, 'McDonaldâ€™s McMuffi Egg'),
(172, 290, 'Wendyâ€™s Jr. Cheeseburger'),
(173, 287, 'Dosa'),
(174, 284, 'Lasagne'),
(175, 283, 'Spelt Bran'),
(176, 283, 'Bratwurst'),
(177, 281, 'Strawberry Sundae'),
(178, 280, 'Butter Pecan Ice Cream'),
(179, 280, 'Grilled Pizza'),
(180, 279, 'Hamburger'),
(181, 272, 'Pizza'),
(182, 271, 'Magnum Almond'),
(183, 268, 'Grilled Chicken Salad'),
(184, 266, 'Chili con Carne'),
(185, 265, 'Ice Cream Sandwich'),
(186, 264, 'Broccoli Cheese Soup'),
(187, 263, 'Veggie Pizza'),
(188, 260, 'Naan'),
(189, 260, 'Paratha'),
(190, 258, 'Magnum'),
(191, 255, 'Magnum White'),
(192, 254, 'Calabrese Pizza'),
(193, 253, 'Ice Cream Sundae'),
(194, 250, 'Chicken Parmesan'),
(195, 250, 'Jambalaya'),
(196, 250, 'Four Cheese Pizza'),
(197, 248, 'Lobster Bisque Soup'),
(198, 248, 'Napoli Pizza'),
(199, 244, 'Baked Beans'),
(200, 240, 'Roast Dinner'),
(201, 240, 'Ciao Bella'),
(202, 234, 'Pomegranate'),
(203, 234, 'Pomegranate'),
(204, 233, 'Dampfnudel'),
(205, 231, 'Oat Bran'),
(206, 231, 'Pomelo'),
(207, 230, 'Vanilla Cone'),
(208, 229, 'Veggie Delight'),
(209, 227, 'Cabbage'),
(210, 222, 'Durum Wheat Semolina'),
(211, 222, 'French Fries'),
(212, 221, 'Succotash'),
(213, 218, 'Plantains'),
(214, 218, 'Plantain'),
(215, 215, 'Papaya'),
(216, 213, 'Taco'),
(217, 210, 'Ben and Jerryâ€™s'),
(218, 207, 'Broccoli'),
(219, 207, 'Orecchiette'),
(220, 207, 'Penne Rigate'),
(221, 207, 'Spaghetti'),
(222, 207, 'Tagliatelle'),
(223, 206, 'Broccoli Soup'),
(224, 206, 'Spaetzle'),
(225, 206, 'Spirelli'),
(226, 206, 'Vermicelli'),
(227, 205, 'McFlurry'),
(228, 202, 'Mango'),
(229, 202, 'Mango'),
(230, 200, 'Paella'),
(231, 200, 'Peanut Butter Sandwich'),
(232, 200, 'Farfalle'),
(233, 200, 'Linguine'),
(234, 200, 'Manicotti'),
(235, 200, 'Orzo'),
(236, 200, 'Pierogi'),
(237, 200, 'Egg Roll'),
(238, 198, 'Tandoori Chicken'),
(239, 198, 'Capellini'),
(240, 198, 'Fettuccine'),
(241, 198, 'Rigatoni'),
(242, 198, 'Rotini'),
(243, 197, 'Fusilli'),
(244, 197, 'Penne'),
(245, 197, 'Whole Grain Spaghetti'),
(246, 197, 'Ziti'),
(247, 195, 'Chicken Tikka Masala'),
(248, 194, 'Whole Grain Noodles'),
(249, 192, 'Potato Soup'),
(250, 192, 'Capricciosa Pizza'),
(251, 191, 'Soft Serve'),
(252, 190, 'Pea Soup'),
(253, 190, 'Pea Soup'),
(254, 190, 'Turkey Hill'),
(255, 190, 'Shrimp Pizza'),
(256, 186, 'Beef Stew'),
(257, 186, 'Seafood Pizza'),
(258, 185, 'Double Rainbow'),
(259, 184, 'Wasabi'),
(260, 181, 'Pepperoni Pizza'),
(261, 181, 'Salami Pizza'),
(262, 180, 'Snickers Ice Cream'),
(263, 177, 'Schwanâ€™s'),
(264, 177, 'Sausage Pizza'),
(265, 176, 'Cherimoya'),
(266, 174, 'Mashed Potatoes'),
(267, 174, 'New York Style Pizza'),
(268, 173, 'Margherita Pizza'),
(269, 172, 'Mint Chocolate Chip Ice Cream'),
(270, 172, 'Lasagna'),
(271, 171, 'Tortilla Wrap'),
(272, 170, 'Baskin-Robbins'),
(273, 170, 'Coffee Ice Cream'),
(274, 170, 'Strawberry Ice Cream'),
(275, 168, 'Cheese Pizza'),
(276, 167, 'Applesauce'),
(277, 166, 'Magnolia'),
(278, 165, 'Red Pepper Pizza'),
(279, 164, 'Potato'),
(280, 164, 'Ice Milk'),
(281, 161, 'Golden Mushroom Soup'),
(282, 160, 'Tortilla Chips'),
(283, 159, 'Shepherds Pie'),
(284, 158, 'Low Carb Pasta'),
(285, 157, 'Thin Crust Pizza'),
(286, 156, 'Chocolate Ice Cream'),
(287, 155, 'Chocolate Chip Ice Cream'),
(288, 155, 'Sundae'),
(289, 154, 'Hawaiian Pizza'),
(290, 154, 'Mozzarella Pizza'),
(291, 150, 'Spinach Feta Pizza'),
(292, 148, 'Creamed Spinach'),
(293, 147, 'Rutabaga'),
(294, 147, 'Winter Squash'),
(295, 147, 'Thai Soup'),
(296, 146, 'Egg Noodles'),
(297, 145, 'French Vanilla Ice Cream'),
(298, 145, 'Vanilla Ice Cream'),
(299, 145, 'Spinach Pizza'),
(300, 143, 'Jackfruit'),
(301, 143, 'Jackfruit'),
(302, 142, 'Tarte FlambÃ©e'),
(303, 142, 'BBQ Rib'),
(304, 141, 'Sapodilla'),
(305, 140, 'Cream of Broccoli Soup'),
(306, 140, 'Friendlyâ€™s'),
(307, 139, 'Savoury Biscuits'),
(308, 139, 'Lentil Soup'),
(309, 138, 'Goat Cheese Pizza'),
(310, 136, 'Custard Apple'),
(311, 136, 'Potato Salad'),
(312, 134, 'Chinese Cabbage'),
(313, 134, 'Ravioli'),
(314, 130, 'Shrimp Cocktail'),
(315, 130, 'Wheat Germ Oil'),
(316, 130, 'Wedding Soup'),
(317, 130, 'Cookie Dough Ice Cream'),
(318, 130, 'Pizza Dough'),
(319, 128, 'Parsnips'),
(320, 128, 'Menhaden Oil'),
(321, 128, 'Salmon Oil'),
(322, 125, 'Fruit salad'),
(323, 125, 'Wheat Bran'),
(324, 125, 'Argan Oil'),
(325, 124, 'Apricot Kernel Oil'),
(326, 124, 'Babassu Oil'),
(327, 124, 'Canola Oil'),
(328, 124, 'Flaxseed Oil'),
(329, 124, 'Grape Seed Oil'),
(330, 124, 'Hazelnut Oil'),
(331, 124, 'Mustard Oil'),
(332, 124, 'Oat Oil'),
(333, 124, 'Poppy Seed Oil'),
(334, 124, 'Rice Bran Oil'),
(335, 124, 'Sesame Oil'),
(336, 124, 'Shea Oil'),
(337, 124, 'Sunflower Oil'),
(338, 124, 'Tomato Seed Oil'),
(339, 124, 'Walnut Oil'),
(340, 123, 'Almond Oil'),
(341, 123, 'Cottonseed Oil'),
(342, 123, 'Palm Kernel Oil'),
(343, 123, 'Palm Oil'),
(344, 123, 'Pumpkin Seed Oil'),
(345, 123, 'Soy Oil'),
(346, 121, 'Soy Noodles'),
(347, 120, 'Avocado Oil'),
(348, 120, 'Coconut Oil'),
(349, 120, 'Corn Oil'),
(350, 120, 'Olive Oil'),
(351, 120, 'Peanut Oil'),
(352, 120, 'Safflower Oil'),
(353, 120, 'Vegetable Oil'),
(354, 120, 'Meatball Soup'),
(355, 120, 'Dragon Fruit'),
(356, 119, 'Durum Wheat Semolina'),
(357, 117, 'Linseed Oil'),
(358, 117, 'Cream of Chicken Soup'),
(359, 116, 'Tomato Rice Soup'),
(360, 116, 'Chicken Breast'),
(361, 115, 'Aubergine'),
(362, 115, 'Eggplant'),
(363, 112, 'Kumara'),
(364, 112, 'Sweet Potato'),
(365, 112, 'Kiwi'),
(366, 112, 'Kiwi'),
(367, 111, 'Banana'),
(368, 111, 'Banana'),
(369, 110, 'Corn Waffles'),
(370, 108, 'Gourd'),
(371, 108, 'Kohlrabi'),
(372, 108, 'Glass Noodles'),
(373, 107, 'Samosa'),
(374, 107, 'Cream of Onion Soup'),
(375, 104, 'Grapes'),
(376, 104, 'Gluten'),
(377, 103, 'Mostaccioli'),
(378, 101, 'Pear'),
(379, 101, 'Black Pudding'),
(380, 101, 'Sausage Rolls'),
(381, 101, 'Sloppy Joe'),
(382, 99, 'Sago'),
(383, 99, 'Breadfruit'),
(384, 97, 'Cream of Mushroom Soup'),
(385, 95, 'Apple'),
(386, 95, 'Shortbread'),
(387, 95, 'Carrot Ginger Soup'),
(388, 95, 'Carrot Soup'),
(389, 95, 'McDonaldâ€™s Mighty Wings'),
(390, 94, 'Chicken Wings'),
(391, 92, 'Cream of Celery Soup'),
(392, 92, 'Cappelletti'),
(393, 90, 'Lettuce'),
(394, 88, 'Squash'),
(395, 87, 'Endive'),
(396, 86, 'Watermelon'),
(397, 86, 'Watermelon'),
(398, 85, 'Cream of Asparagus Soup'),
(399, 85, 'Mushroom Soup'),
(400, 84, 'Blueberries'),
(401, 83, 'Yorkshire Pudding'),
(402, 83, 'Beef Noodle Soup'),
(403, 83, 'Noodle Soup'),
(404, 82, 'Minestrone'),
(405, 82, 'Cannelloni'),
(406, 80, 'Millet Gruel'),
(407, 80, 'Scotch Broth'),
(408, 80, 'Healthy Choice'),
(409, 79, 'Peas'),
(410, 78, 'Spinach'),
(411, 77, 'Chicken Vegetable Soup'),
(412, 76, 'Vegetable Beef Soup'),
(413, 75, 'Fried Shrimp'),
(414, 75, 'Solero'),
(415, 75, 'Asian Pear'),
(416, 74, 'Tomato Soup'),
(417, 73, 'Fennel'),
(418, 73, 'Cream of Potato Soup'),
(419, 71, 'Pumpkin Soup'),
(420, 70, 'Blood Oranges'),
(421, 70, 'Minneola'),
(422, 70, 'Beef Soup'),
(423, 69, 'Cabbage Soup'),
(424, 68, 'Oxtail Soup'),
(425, 67, 'Vegetable Soup'),
(426, 66, 'Cucumber'),
(427, 66, 'Nectarine'),
(428, 64, 'Raspberries'),
(429, 63, 'Currants'),
(430, 62, 'Blackberries'),
(431, 62, 'Orange'),
(432, 62, 'Deviled Eggs'),
(433, 62, 'Alphabet Soup'),
(434, 62, 'Chicken Noodle Soup'),
(435, 60, 'Artichoke'),
(436, 60, 'Mulberries'),
(437, 59, 'Peach'),
(438, 59, 'Chicken Nuggets'),
(439, 58, 'Chicken with Rice Soup'),
(440, 58, 'Mangosteen'),
(441, 57, 'Lasagne Sheets'),
(442, 57, 'Falafel'),
(443, 56, 'Chicken Gumbo Soup'),
(444, 56, 'Creamy Chicken Noodle Soup'),
(445, 56, 'French Onion Soup'),
(446, 56, 'Onion Soup'),
(447, 55, 'Dumpling Dough'),
(448, 54, 'Leek'),
(449, 52, 'Quince'),
(450, 52, 'Grapefruit'),
(451, 52, 'Pink Grapefruit'),
(452, 51, 'Pumpkin'),
(453, 49, 'Strawberries'),
(454, 48, 'Mandarin Oranges'),
(455, 48, 'Chicken McNuggets'),
(456, 48, 'McDonaldâ€™s Chicken Nuggets'),
(457, 47, 'Mandarin Oranges'),
(458, 47, 'Tangerine'),
(459, 46, 'Cranberries'),
(460, 46, 'Pretzel Sticks'),
(461, 45, 'Honeydew'),
(462, 44, 'Smoked Salmon'),
(463, 43, 'Ravioli'),
(464, 41, 'Rusk'),
(465, 39, 'Bouillon'),
(466, 39, 'Chicken Broth'),
(467, 39, 'Chicken Stock'),
(468, 38, 'Chicory'),
(469, 37, 'Figs'),
(470, 37, 'Guava'),
(471, 37, 'Dim Sum'),
(472, 37, 'Guava'),
(473, 35, 'Beetroot'),
(474, 35, 'Clementine'),
(475, 35, 'Cracker'),
(476, 35, 'Pizza Rolls'),
(477, 35, 'Casaba Melon'),
(478, 34, 'Green Beans'),
(479, 34, 'Onion'),
(480, 34, 'Turnip Greens'),
(481, 34, 'Turnips'),
(482, 33, 'Courgette'),
(483, 33, 'Kale'),
(484, 33, 'Zucchini'),
(485, 33, 'California Roll'),
(486, 32, 'Persimmon'),
(487, 31, 'Barley Groats'),
(488, 30, 'Plum'),
(489, 30, 'Mini Milk'),
(490, 30, 'Galia Melon'),
(491, 29, 'Turkey'),
(492, 28, 'Starfruit'),
(493, 28, 'Star Fruit'),
(494, 25, 'Carrot'),
(495, 25, 'Onion Rings'),
(496, 24, 'Tuna'),
(497, 23, 'Cantaloupe'),
(498, 23, 'Roast Beef'),
(499, 23, 'Cantaloupe Melon'),
(500, 23, 'Feijoa'),
(501, 23, 'Muskmelon'),
(502, 22, 'Jujube'),
(503, 20, 'Cherry Tomato'),
(504, 20, 'Pepper'),
(505, 20, 'Tomato'),
(506, 20, 'Acai'),
(507, 20, 'Dates'),
(508, 20, 'Lime'),
(509, 20, 'Grissini'),
(510, 18, 'Shallots'),
(511, 17, 'Apricot'),
(512, 17, 'Lemon'),
(513, 17, 'Passion Fruit'),
(514, 17, 'Maracuya'),
(515, 17, 'Passion Fruit'),
(516, 16, 'Prawn Crackers'),
(517, 15, 'Bell Pepper'),
(518, 15, 'Mustard Greens'),
(519, 15, 'Noni'),
(520, 13, 'Collard Greens'),
(521, 13, 'Kumquat'),
(522, 12, 'Capsicum'),
(523, 12, 'Collard Greens'),
(524, 12, 'Vegetable Broth'),
(525, 12, 'Vegetable Stock'),
(526, 11, 'Rhubarb'),
(527, 10, 'Wheat Gluten'),
(528, 10, 'Chicken Bouillon'),
(529, 10, 'Shirataki Noodles'),
(530, 9, 'Chard'),
(531, 9, 'Gherkin'),
(532, 8, 'Brussels Sprouts'),
(533, 8, 'Maraschino Cherries'),
(534, 8, 'Prickly Pear'),
(535, 7, 'Horseradish'),
(536, 7, 'Red Cabbage'),
(537, 7, 'Lychees'),
(538, 7, 'Rambutan'),
(539, 7, 'Beef Bouillon'),
(540, 7, 'Lychee'),
(541, 7, 'Rambutan'),
(542, 6, 'Celery'),
(543, 5, 'Green Onion'),
(544, 5, 'Tamarind'),
(545, 5, 'Tamarind'),
(546, 4, 'Garlic'),
(547, 4, 'Okra'),
(548, 4, 'Cherries'),
(549, 3, 'Cauliflower'),
(550, 2, 'Asparagus'),
(551, 2, 'Black Olives'),
(552, 2, 'Green Olives'),
(553, 2, 'Olives'),
(554, 2, 'Greengage'),
(555, 2, 'Olives'),
(556, 2, 'Physalis'),
(557, 1, 'Arugula'),
(558, 1, 'Chives'),
(559, 1, 'Mushrooms'),
(560, 1, 'Nori'),
(561, 1, 'Radishes'),
(562, 1, 'Acerola');

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `id` int(11) NOT NULL,
  `is_logout` tinyint(4) NOT NULL,
  `token` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `token`
--

INSERT INTO `token` (`id`, `is_logout`, `token`) VALUES
(1, 1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJzeWFmaXEiLCJpYXQiOjE3MDI1NjkwMTMsImV4cCI6MTcwMjU3MjYxM30.z-N8bk0-S0T8wUkwf9xOT3eD5hoqskdqd2PR2k2rW34'),
(2, 0, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJzeWFmaXEiLCJpYXQiOjE3MDI1NjkwNjYsImV4cCI6MTcwMjU3MjY2Nn0.tibd3Gj9TiT5G_pocRujX6qgvBVyl6yVvopa7Ht39lQ'),
(3, 0, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJzeWFmaXEiLCJpYXQiOjE3MDI1NjkzNjcsImV4cCI6MTcwMjU3Mjk2N30.xNerKsPR170bBXTAN1TgAU3XkfuJ2vOIIC4Scbe800g'),
(4, 1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsIm5hbWUiOiJzeWFmaXEiLCJpYXQiOjE3MDI1NzA5OTksImV4cCI6MTcwMjU3NDU5OX0.HYFIfBybuPnyjojCDvn81AgjB3nIGo_-bVVZoOOuh9s'),
(5, 0, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsIm5hbWUiOiJzeWFmaXEiLCJpYXQiOjE3MDI1NzEwOTEsImV4cCI6MTcwMjU3NDY5MX0.TOUt3_qANRhc3-WZSskvkzlXjNJY3Il0lmu1uq_xYAs'),
(6, 1, 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjIsIm5hbWUiOiJzeWFmaXEiLCJpYXQiOjE3MDI1NzEwOTYsImV4cCI6MTcwMjU3NDY5Nn0.KV8rqm9j9H213DNlp8qzL7VjvOrgQ9m3bxLJe8GwOiI');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `email`, `password`) VALUES
(1, 'syafiq', 'syafiq2@gmail.com', '12345678'),
(2, 'syafiq', 'syafiq3@gmail.com', '12345678');

-- --------------------------------------------------------

--
-- Table structure for table `workout`
--

CREATE TABLE `workout` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `calories` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `consumption`
--
ALTER TABLE `consumption`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_c8f2ddde9bde8c26ba5aeae1bcf` (`user_id`),
  ADD KEY `FK_31935d24b417312bd7755355517` (`food_id`);

--
-- Indexes for table `diet`
--
ALTER TABLE `diet`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `IDX_e12875dfb3b1d92d7d7c5377e2` (`email`);

--
-- Indexes for table `workout`
--
ALTER TABLE `workout`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `consumption`
--
ALTER TABLE `consumption`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `diet`
--
ALTER TABLE `diet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=563;

--
-- AUTO_INCREMENT for table `token`
--
ALTER TABLE `token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `workout`
--
ALTER TABLE `workout`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `consumption`
--
ALTER TABLE `consumption`
  ADD CONSTRAINT `FK_31935d24b417312bd7755355517` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_c8f2ddde9bde8c26ba5aeae1bcf` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
