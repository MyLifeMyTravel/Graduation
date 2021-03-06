-- phpMyAdmin SQL Dump
-- version 3.3.8.1
-- http://www.phpmyadmin.net
--
-- Host: w.rdc.sae.sina.com.cn:3307
-- Generation Time: Apr 27, 2015 at 10:56 AM
-- Server version: 5.5.23
-- PHP Version: 5.3.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `app_wodebiyesheji`
--

-- --------------------------------------------------------

--
-- Table structure for table `cdlx`
--

CREATE TABLE IF NOT EXISTS `cdlx` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cdlx`
--

INSERT INTO `cdlx` (`id`, `name`) VALUES
(1, '供电线路\r\n'),
(2, '10KV 配电室\r\n'),
(3, '主变压器\r\n'),
(4, '主变压器断路器\r\n'),
(5, '110KV 母线\r\n'),
(6, '110KV 母电压互感器\r\n'),
(7, '35KV 母电压互感器\r\n'),
(8, '主控室\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `dept`
--

CREATE TABLE IF NOT EXISTS `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` varchar(255) NOT NULL,
  `dDescribe` varchar(255) NOT NULL,
  `dName` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `dept`
--

INSERT INTO `dept` (`id`, `createTime`, `dDescribe`, `dName`) VALUES
(1, '2000-01-01', '主管人事', '人事部'),
(2, '2000-01-01', '负责设备巡检', '巡检部');

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE IF NOT EXISTS `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sblx_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `device`
--


-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE IF NOT EXISTS `history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `place_id` int(11) NOT NULL,
  `device_id` int(11) NOT NULL,
  `xsnr_id` int(11) NOT NULL,
  `problems` varchar(255) DEFAULT NULL,
  `time` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `history_task` (`task_id`) USING BTREE,
  KEY `history_place` (`place_id`) USING BTREE,
  KEY `history_device` (`device_id`) USING BTREE,
  KEY `history_xsnr` (`xsnr_id`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=38 ;

--
-- Dumping data for table `history`
--

INSERT INTO `history` (`id`, `task_id`, `place_id`, `device_id`, `xsnr_id`, `problems`, `time`) VALUES
(34, 2, 11, 2, 8, '[{"note":"u5149u7ebfu4e0du8db3","xsbz_id":31}]', '2015-04-26 11:47:42'),
(32, 2, 15, 14, 52, '[{"note":"u4f60u597d","xsbz_id":145}]', '2015-04-25 19:05:28'),
(33, 2, 14, 9, 37, '[{"note":"u6cb9u4f4du4f4du4e8eu4e0au6807u5fd7u7ebfu4e0a","xsbz_id":112}]', '2015-04-25 19:14:53'),
(35, 2, 11, 3, 10, '[{"note":"u6cb9u6f06u8131u843d","xsbz_id":36}]', '2015-04-26 14:54:13'),
(36, 2, 11, 3, 12, '[{"note":"u5916u58f3u7834u635f","xsbz_id":41}]', '2015-04-26 14:54:34'),
(37, 2, 14, 9, 38, '[{"note":"u6cb9u8272u4e3au9ed1u8272","xsbz_id":114}]', '2015-04-26 14:56:35');

-- --------------------------------------------------------

--
-- Table structure for table `place`
--

CREATE TABLE IF NOT EXISTS `place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cdlx_id` int(11) NOT NULL,
  `site_id` int(11) NOT NULL,
  `identifier` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `place_site` (`site_id`) USING BTREE,
  KEY `place_cdlx` (`cdlx_id`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `place`
--

INSERT INTO `place` (`id`, `cdlx_id`, `site_id`, `identifier`, `name`) VALUES
(4, 1, 1, '新浦973线', '新浦973线'),
(5, 2, 1, '10KV 配电室', '10KV 配电室'),
(6, 3, 1, '1号主变压器', '1号主变压器'),
(7, 4, 1, '1号主变压器断路器', '1号主变压器断路器'),
(8, 3, 1, '2号主变压器', '2号主变压器'),
(9, 4, 1, '2号主变压器断路器', '2号主变压器断路器'),
(10, 8, 1, '主控室', '主控室'),
(11, 2, 2, '宁大本部10KV 变电室', '宁大本部10KV 变电室'),
(12, 8, 2, '宁大变电站主控室', '宁大变电站主控室'),
(13, 1, 2, '宁大南门供电线路', '宁大南门供电线路'),
(14, 3, 2, '主变压器', '主变压器'),
(15, 4, 2, '主变压器断路器', '主变压器断路器');

-- --------------------------------------------------------

--
-- Table structure for table `position`
--

CREATE TABLE IF NOT EXISTS `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deptID` int(11) NOT NULL,
  `pName` varchar(255) NOT NULL,
  `pDescribe` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `pDeptID` (`deptID`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `position`
--

INSERT INTO `position` (`id`, `deptID`, `pName`, `pDescribe`) VALUES
(1, 2, '巡检员', '负责设备巡检'),
(2, 1, '人事经理', '负责人事');

-- --------------------------------------------------------

--
-- Table structure for table `rwlx`
--

CREATE TABLE IF NOT EXISTS `rwlx` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `rwlx`
--

INSERT INTO `rwlx` (`id`, `name`, `description`) VALUES
(1, '日常巡视', '日常巡视，每日至少一次'),
(2, '夜间熄灯巡视', '夜间熄灯巡视，每周至少一次'),
(3, '特殊巡视', '重点设备、薄弱设备巡视'),
(4, '高峰负荷巡视', '用电高峰负荷巡视'),
(6, '高温巡视', '高温季节巡视'),
(7, '大风天气巡视', '大风天气特殊巡视'),
(8, '大雨天气巡视', '大雨天气特殊巡视'),
(9, '大雪巡视', '大雪天气巡视'),
(10, '大雾巡视', '大雾天气巡视'),
(11, '凝冻巡视', '凝冻天气巡视');

-- --------------------------------------------------------

--
-- Table structure for table `sblx`
--

CREATE TABLE IF NOT EXISTS `sblx` (
  `id` int(11) NOT NULL,
  `cdlx_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `params` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sblx_cdlx` (`cdlx_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sblx`
--

INSERT INTO `sblx` (`id`, `cdlx_id`, `name`, `params`) VALUES
(1, 1, '线路开关\r\n', ''),
(2, 2, '高压室\r\n', ''),
(3, 2, '电压互感器\r\n', ''),
(4, 2, '主变低压侧开关柜\r\n', ''),
(5, 2, '线路开关\r\n', ''),
(6, 2, '站用变\r\n', ''),
(7, 2, '站用变本体\r\n', ''),
(8, 3, '主变本体\r\n', ''),
(9, 3, '主变套管\r\n', ''),
(10, 3, '主变外部主导流部位\r\n', ''),
(11, 3, '主变风冷系统\r\n', ''),
(12, 3, '主变中性点设备\r\n', ''),
(13, 3, '主变端子箱\r\n', ''),
(14, 4, '主变中压侧开关柜\r\n', ''),
(15, 5, '母线\r\n', ''),
(16, 6, '电压互感器\r\n', ''),
(17, 6, '避雷器\r\n', ''),
(18, 6, '隔离开关\r\n', ''),
(19, 7, '电压互感器\r\n', ''),
(20, 8, '主控室\r\n', ''),
(21, 8, '远动屏\r\n', ''),
(22, 8, '主变及各线路测控屏\r\n', ''),
(23, 8, '主控桌设备\r\n', ''),
(24, 8, '直流系统\r\n', ''),
(25, 8, '1号主变保护屏\r\n', ''),
(26, 8, '备自投屏\r\n', '');

-- --------------------------------------------------------

--
-- Table structure for table `site`
--

CREATE TABLE IF NOT EXISTS `site` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identifier` varchar(255) NOT NULL,
  `loc` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `point` varchar(255) DEFAULT NULL,
  `province` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `area` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `site`
--

INSERT INTO `site` (`id`, `identifier`, `loc`, `name`, `point`, `province`, `city`, `area`, `street`) VALUES
(1, '筱村35KV变电站', '浙江省温州市泰顺县筱村镇', '筱村35KV变电站', NULL, '浙江省', '温州市', '泰顺县', '筱村'),
(2, '宁波大学110KV变电站', '浙江省宁波市江北区风华路818号宁波大学', '宁波大学110KV变电站', NULL, '浙江省', '宁波市', '江北区', '风华路');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rwlx_id` int(11) NOT NULL,
  `task_from` varchar(255) NOT NULL,
  `task_to` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `site_id` int(11) NOT NULL,
  `flag` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `task_from` (`task_from`) USING BTREE,
  KEY `task_rwlx` (`rwlx_id`) USING BTREE,
  KEY `task_to` (`task_to`) USING BTREE,
  KEY `task_site` (`site_id`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id`, `rwlx_id`, `task_from`, `task_to`, `date`, `time`, `site_id`, `flag`) VALUES
(5, 7, 'admin', '116040384', '2015-04-26', '10:50', 2, 'unfinished'),
(6, 4, 'admin', '116040384', '2015-04-26', '14:30', 1, 'unfinished'),
(7, 1, 'admin', '116040384', '2015-04-27', '8:00', 1, 'unfinished'),
(8, 2, 'admin', '116040384', '2015-04-26', '22:00', 2, 'unfinished'),
(9, 7, 'admin', '116040384', '2015-04-27', '12:00', 2, 'unfinished'),
(10, 2, 'admin', '116040384', '2015-04-27', '22:00', 2, 'unfinished');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `deptID` int(11) NOT NULL,
  `positionID` int(11) NOT NULL,
  `joinTime` varchar(255) NOT NULL,
  `device_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `positionID` (`positionID`) USING BTREE,
  KEY `uDeptID` (`deptID`) USING BTREE,
  KEY `account` (`account`) USING BTREE
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `account`, `name`, `password`, `pic`, `description`, `phone`, `email`, `deptID`, `positionID`, `joinTime`, `device_id`) VALUES
(1, '116040384', '厉圣杰', '111111', '/img/user/116040384.jpg', '初次见面，请多指教！', '18815283020', '1025263614@qq.com', 2, 1, '2008-01-01', '865800029557771'),
(2, '116040383', 'Boss', '111111', NULL, '初次见面，请多指教！', '18067523878', '1025263614lsj@gmail.com', 1, 2, '2008-01-01', NULL),
(3, 'admin', '管理员', '111111', NULL, '我是管理员', '18815283020', '1025263614@qq.com', 1, 2, '2000-01-01', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `xsbz`
--

CREATE TABLE IF NOT EXISTS `xsbz` (
  `id` int(11) NOT NULL,
  `sblx_id` int(11) NOT NULL,
  `xsnr_id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `xsbz_sblx` (`sblx_id`) USING BTREE,
  KEY `xsbz_xsnr` (`xsnr_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `xsbz`
--

INSERT INTO `xsbz` (`id`, `sblx_id`, `xsnr_id`, `description`) VALUES
(1, 1, 1, '开关柜外观检查无异常，柜门严密，柜体无倾斜，油漆无脱落\r\n'),
(2, 1, 1, '防误闭锁装置锁具完好，闭锁可靠\r\n'),
(3, 1, 1, '带电显示装置清晰，指示正确\r\n'),
(4, 1, 1, '检查开关柜温度正常，必要时应使用测温仪对设备进行检测\r\n'),
(5, 1, 2, '外壳无裂纹、炭化、融化、烧痕和冒烟现象，并无异常气味\r\n'),
(6, 1, 2, '内部无异常响声、接头无过热现象\r\n'),
(7, 1, 3, '检查开关机构分、合闸指示器指示正确，无偏差，与实际运行状态一致\r\n'),
(8, 1, 3, '开关应无任何异常声音\r\n'),
(9, 1, 3, '停电后传动连杆无弯曲变形、轴销齐全，无松动、脱落\r\n'),
(10, 1, 3, '停电后检查开关动、静触头完好\r\n'),
(11, 1, 3, '二次线无松脱及发热变色现象。电缆标志正确、清晰，孔洞封堵严密\r\n'),
(12, 1, 4, '电缆终端头应无溢胶、放电、异常响声，套管清洁、无损伤\r\n'),
(13, 1, 4, '电缆接头接触良好，无发热现象\r\n'),
(14, 1, 4, '电缆终端头屏蔽层接地良好；电缆标志正确，相色标志清晰，孔洞封堵严密\r\n'),
(15, 1, 4, '电缆终端头无变形，相间及对地距离符合规定（可以开关相间距离判断）\r\n'),
(16, 1, 5, '开关柜屏面上的控制指示正常\r\n'),
(17, 1, 5, '保护装置正常、无异常声音及气味\r\n'),
(18, 1, 5, '保护装置电源和“运行”灯亮，面板上各指示灯指示正确，符合装置说明书规定。装置显示器的显示信息正确\r\n'),
(19, 1, 5, '保护压板投、退位置正确\r\n'),
(20, 1, 5, '二次线无松脱、发热变色现象，电缆孔洞封堵严密\r\n'),
(21, 1, 6, '在合闸位置的刀闸，应合到位，主接触部位接触良好，闭锁、定位良好\r\n'),
(22, 1, 6, '观察接头有无发热、变色、氧化\r\n'),
(23, 1, 6, '必要时应使用测温仪对设备进行检测\r\n'),
(24, 1, 6, '瓷质部分应完好、清洁，无破损、放电痕迹\r\n'),
(25, 1, 6, '防误闭锁装置锁具完好，闭锁可靠。机械联锁装置应完整可靠\r\n'),
(26, 1, 6, '传动连杆无弯曲变形、轴销齐全，无松动、脱落、锈蚀\r\n'),
(27, 2, 7, '检查门窗关闭严密，玻璃完整\r\n'),
(28, 2, 7, '雨雪天气检查房屋无渗、漏水现象\r\n'),
(29, 2, 7, '放火设施齐备、完好。灭火器材在有效使用期内\r\n'),
(30, 2, 7, '室内应无散落器材，没有危险品\r\n'),
(31, 2, 8, '室内照明和事故照明应保持完好，无缺陷。光线充足\r\n'),
(32, 2, 8, '室内温度应控制在10-26℃，否则应采取通风设备\r\n'),
(33, 2, 9, '电缆沟内清洁，无进水现象。电缆排放整齐\r\n'),
(34, 2, 9, '电缆沟孔洞封堵严密\r\n'),
(35, 2, 9, '防鼠、防小动物设施齐备，措施完好\r\n'),
(36, 3, 10, '开关柜外观检查无异常，柜门严密，柜体无倾斜，油漆无脱落\r\n'),
(37, 3, 10, '带电显示装置清晰，指示正确\r\n'),
(38, 3, 10, '检查开关柜温度正常，必要时应使用测温仪对设备进行检测\r\n'),
(39, 3, 11, '(停电后)检查套管清洁，无破损、裂纹、放电痕迹\r\n'),
(40, 3, 11, '内部无任何异常声音、气味\r\n'),
(41, 3, 12, '外壳接地良好\r\n'),
(42, 4, 13, '检查母线固定部位有无窜动等应力现象\r\n'),
(43, 4, 13, '检查封闭柜有无破损及放电迹象\r\n'),
(44, 4, 14, '检查瓷质部分完好清洁、无破损、放电痕迹\r\n'),
(45, 4, 15, '开关柜外观检查无异常，柜门严密，柜体无倾斜，油漆无脱落\r\n'),
(46, 4, 15, '防误闭锁装置锁具完好，闭锁可靠\r\n'),
(47, 4, 15, '带电显示装置清晰，指示正确\r\n'),
(48, 4, 15, '检查开关柜温度正常，必要时应使用测温仪对设备进行检测\r\n'),
(49, 4, 16, '外壳无裂纹、炭化、融化、烧痕和冒烟现象，并无异常气味\r\n'),
(50, 4, 16, '内部无异常响声、接头无过热现象\r\n'),
(51, 4, 17, '检查开关机构分、合闸指示器指示正确，无偏差，与实际运行状态一致\r\n'),
(52, 4, 17, '开关应无任何异常声音\r\n'),
(53, 4, 17, '停电后检查传动连杆无弯曲变形、轴销齐全，无松动、脱落、动、静触头完好\r\n'),
(54, 4, 17, '停电后检查开关动、静触头良好完好\r\n'),
(55, 4, 17, '二次线无松脱及发热变色现象。电缆标志正确、清晰，孔洞封堵严密\r\n'),
(56, 4, 18, '引接线及接地部位接触良好\r\n'),
(57, 4, 18, '外观良好，无破损\r\n'),
(58, 5, 19, '开关柜外观检查无异常，柜门严密，柜体无倾斜，油漆无脱落\r\n'),
(59, 5, 19, '防误闭锁装置锁具完好，闭锁可靠\r\n'),
(60, 5, 19, '带电显示装置清晰，指示正确\r\n'),
(61, 5, 19, '检查开关柜温度正常，必要时应使用测温仪对设备进行检测\r\n'),
(62, 5, 20, '外壳无裂纹、炭化、融化、烧痕和冒烟现象，并无异常气味\r\n'),
(63, 5, 20, '内部无异常响声、接头无过热现象\r\n'),
(64, 5, 21, '检查开关机构分、合闸指示器指示正确，无偏差，与实际运行状态一致\r\n'),
(65, 5, 21, '开关应无任何异常声音\r\n'),
(66, 5, 21, '停电后传动连杆无弯曲变形、轴销齐全，无松动、脱落\r\n'),
(67, 5, 21, '停电后检查开关动、静触头完好\r\n'),
(68, 5, 21, '二次线无松脱及发热变色现象。电缆标志正确、清晰，孔洞封堵严密\r\n'),
(69, 5, 22, '电缆终端头应无溢胶、放电、异常响声，套管清洁、无损伤\r\n'),
(70, 5, 22, '电缆接头接触良好，无发热现象\r\n'),
(71, 5, 22, '电缆终端头屏蔽层接地良好；电缆标志正确，相色标志清晰，孔洞封堵严密\r\n'),
(72, 5, 22, '电缆终端头无变形，相间及对地距离符合规定（可以开关相间距离判断）\r\n'),
(73, 5, 23, '开关柜屏面上的控制指示正常\r\n'),
(74, 5, 23, '保护装置正常、无异常声音及气味\r\n'),
(75, 5, 23, '保护装置电源和“运行”灯亮，面板上各指示灯指示正确，符合装置说明书规定。装置显示器的显示信息正确\r\n'),
(76, 5, 23, '保护压板投、退位置正确\r\n'),
(77, 5, 23, '二次线无松脱、发热变色现象，电缆孔洞封堵严密\r\n'),
(78, 5, 24, '引接线及接地部位接触良好\r\n'),
(79, 5, 24, '外观良好，无破损\r\n'),
(80, 6, 25, '开关柜外观检查无异常，柜门严密，柜体无倾斜，油漆无脱落\r\n'),
(81, 6, 25, '防误闭锁装置锁具完好，闭锁可靠\r\n'),
(82, 6, 25, '检查开关柜温度正常，必要时应使用测温仪对设备进行检测\r\n'),
(83, 6, 26, '检查保险无熔断，接触良好\r\n'),
(84, 6, 26, '保险瓷套清洁、无裂纹和放电痕迹\r\n'),
(85, 6, 27, '电缆终端头应无溢胶、放电、异常响声，套管清洁、无损伤\r\n'),
(86, 6, 27, '电缆接头接触良好，无发热现象\r\n'),
(87, 6, 27, '电缆终端头屏蔽层接地良好；电缆标志正确，相色标志清晰，孔洞封堵严密\r\n'),
(88, 6, 27, '电缆终端头无变形，相间及对地距离符合规定\r\n'),
(89, 6, 27, '电缆外皮无损伤\r\n'),
(90, 7, 28, '响声正常，无杂音或不均匀的放电声\r\n'),
(91, 7, 29, '本体及套管连接处无变形现象\r\n'),
(92, 7, 29, '套管完好、无破损、清洁、放电痕迹\r\n'),
(93, 7, 29, '接地极焊接牢固，无锈蚀现象\r\n'),
(94, 7, 29, '呼吸器内硅胶颜色无受潮变色。如硅胶变为红色，且变色部分超过1/3，应更换硅胶\r\n'),
(95, 8, 30, '变压器本体温度计完好、无破损\r\n'),
(96, 8, 30, '记录变压器上层油温数值，上层油温限值85℃、温升限值：45℃\r\n'),
(97, 8, 30, '主控室远方测温数值正确，与主变本体温度指示数值相符。将变压器各部位所装温度计的指示相互对照、比较，防止误判断\r\n'),
(98, 8, 30, '相同运行条件下，上层油温比平时高10℃及以上，或负荷不变但油温不断上升，均为异常\r\n'),
(99, 8, 31, '变压器的油位指示，应和油枕上的环境温度标志线相对应、无大偏差\r\n'),
(100, 8, 31, '正常油色应为透明的淡黄色\r\n'),
(101, 8, 31, '油位计应无破损和渗漏油，没有影响察看油位的油垢\r\n'),
(102, 8, 32, '检查有无渗漏油，要记录清楚渗漏的部位、程度\r\n'),
(103, 8, 32, '设备本体附着有油、灰的部位，必要时进行清擦；可以利用多次巡视机会检查现象，鉴别是否渗油缺陷\r\n'),
(104, 8, 32, '渗漏油的部位，1min超过1滴，属于漏油\r\n'),
(105, 8, 33, '瓦斯继电器内应充满油，油色应为淡黄色透明，无渗漏油。瓦斯继电器内应无气体（泡）\r\n'),
(106, 8, 33, '瓦斯继电器防雨措施完好、防雨罩牢固\r\n'),
(107, 8, 33, '瓦斯继电器的引出二次电缆应无油迹和腐蚀现象，无松脱\r\n'),
(108, 8, 34, '变压器正常应为均匀的嗡嗡声音，无放电等异音\r\n'),
(109, 8, 35, '压力释放器有无油迹，二次电缆及护管无破损或被油腐蚀\r\n'),
(110, 8, 36, '硅胶颜色无受潮变色。如硅胶变为红色，且变色部分超过1/3，应更换硅胶\r\n'),
(111, 8, 36, '呼吸器外部无油迹。油杯完好，油位正常\r\n'),
(112, 9, 37, '油位，应在上、下油位标志线之间\r\n'),
(113, 9, 37, '油位计应无破损和渗漏油，没有影响察看油位的油垢\r\n'),
(114, 9, 38, '正常油色应为透明的淡黄色\r\n'),
(115, 9, 39, '应清洁，无破损、裂纹、无放电声\r\n'),
(116, 9, 40, '应无裂纹和严重锈蚀\r\n'),
(117, 10, 41, '引线线夹压接牢固、接触良好，无变色、变形，铜铝过渡部位无裂纹\r\n'),
(118, 10, 41, '主导流接触部位，看有无变色、有无氧化加剧、有无热气流上升、夜间有无发红发热等\r\n'),
(119, 10, 41, '雨雪天气，检查主导流接触部位，看有无积雪融化、水蒸气现象\r\n'),
(120, 10, 41, '以上检查，若需要鉴定，应使用测温仪对设备进行检测\r\n'),
(121, 10, 42, '引线无断股、无烧伤痕迹\r\n'),
(122, 10, 42, '发现引线若有散股现象，应仔细辨认有无损伤、断股\r\n'),
(123, 10, 42, '检查母线、导线弧垂变化是否明显，有无挂落异物\r\n'),
(124, 11, 43, '散热装置清洁，散热片不应有过多的积灰等附着脏物\r\n'),
(125, 12, 44, '符合电网运行要求，与变压器有关保护投退方式相对应\r\n'),
(126, 12, 45, '引线完好，接触牢靠，线夹无裂纹．套管无破损、裂纹，引线连接良好\r\n'),
(127, 12, 45, '无渗漏油现象\r\n'),
(128, 12, 46, '完好、无松脱及脱焊\r\n'),
(129, 12, 47, '清洁无损、无放电现象，法兰无裂纹锈蚀现象\r\n'),
(130, 12, 47, '内部应无响声，本体无倾斜\r\n'),
(131, 12, 47, '放电计数器是否完好，记录动作次数\r\n'),
(132, 13, 48, '箱内清洁，箱门关闭严密\r\n'),
(133, 13, 49, '各接触器接触良好，无发热现象和异常响声\r\n'),
(134, 13, 49, '箱内加热器、照明均正常\r\n'),
(135, 13, 49, '箱内接线无松动、无脱落、无发热痕迹\r\n'),
(136, 13, 49, '孔洞封堵严密\r\n'),
(137, 14, 50, '观察穿墙套管接头线夹有无变色严重、氧化加剧、夜间熄灯察看有无发红发热等\r\n'),
(138, 14, 50, '以上检查，若需要鉴定，应使用测温仪对设备进行检测\r\n'),
(139, 14, 50, '检查母线固定部位有无窜动等应力现象\r\n'),
(140, 14, 50, '检查封闭柜破损及放电迹象\r\n'),
(141, 14, 51, '开关柜外观检查无异常，柜门严密，柜体无倾斜，油漆无脱落\r\n'),
(142, 14, 51, '防误闭锁装置锁具完好，闭锁可靠。机械联锁装置完整可靠\r\n'),
(143, 14, 51, '带电显示装置清晰，指示正确\r\n'),
(144, 14, 51, '检查开关柜温度正常，必要时应使用测温仪对设备进行检测\r\n'),
(145, 14, 52, '外壳无裂纹、炭化、融化、烧痕和冒烟现象，并无异常气味\r\n'),
(146, 14, 52, '内部无异常响声、接头无过热现象\r\n'),
(147, 14, 53, '检查开关机构分、合闸指示器指示正确，无偏差，与实际运行状态一致\r\n'),
(148, 14, 53, '开关应无任何异常声音\r\n'),
(149, 14, 53, '停电后检查传动连杆无弯曲变形、轴销齐全，无松动、脱落\r\n'),
(150, 14, 53, '停电后检查开关动、静触头良好完好\r\n'),
(151, 14, 53, '二次线无松脱及发热变色现象。电缆标志正确、清晰，孔洞封堵严密\r\n'),
(152, 14, 54, '引接线及接地部位接触良好\r\n'),
(153, 14, 54, '外观良好，无破损\r\n'),
(154, 14, 54, '引接线及接地部位接触良好\r\n'),
(155, 14, 54, '外观良好，无破损\r\n'),
(156, 15, 55, '检查接头有无松动、断片\r\n'),
(157, 15, 55, '观察接头有无热气流、变色严重、氧化加剧、夜间熄灯察看有无发红、发热现象\r\n'),
(158, 15, 55, '雨雪天气，检查设备引线、线夹主导流接触部位、刀闸主接触部位，看有无积雪融化、水蒸气现象\r\n'),
(159, 15, 55, '以上检查，若需要鉴定，应使用测温仪对设备进行检测\r\n'),
(160, 15, 55, '检查母线固定部位有无窜动等应力现象\r\n'),
(161, 15, 55, '无挂落异物\r\n'),
(162, 15, 56, '无污脏、破损及放电迹象\r\n'),
(163, 15, 57, '无锈蚀、变形、裂纹、损坏、基础无沉降\r\n'),
(164, 16, 58, '清洁、无损、无放电现象\r\n'),
(165, 16, 59, '无渗漏油\r\n'),
(166, 16, 59, '内部无异音、异味\r\n'),
(167, 16, 60, '油标的油位指示，应和环境温度标志线相对应、无大偏差\r\n'),
(168, 16, 60, '正常油色应为透明的淡黄色\r\n'),
(169, 16, 60, '油位计应无破损和渗漏油，没有影响察看油位的油垢\r\n'),
(170, 16, 61, '检查引线线夹压接应牢固、接触良好，无变色、变形、铜铝过渡部位无裂纹\r\n'),
(171, 16, 62, '无异常，指示正确\r\n'),
(172, 17, 63, '清洁无损、无放电现象，法兰无裂纹锈蚀、进水等现象\r\n'),
(173, 17, 64, '内部应无响声，本体无倾斜\r\n'),
(174, 17, 64, '基础无裂缝，固定螺丝无松动、锈蚀\r\n'),
(175, 17, 65, '引线完好，接触牢靠，线夹无裂纹\r\n'),
(176, 17, 66, '接地良好，接地线无锈蚀\r\n'),
(177, 18, 67, '导线无断股\r\n'),
(178, 18, 67, '触头接触良好\r\n'),
(179, 18, 67, '观察接头有无热气流、变色严重、氧化加剧、夜间熄灯巡视察看有无发红发热等现象\r\n'),
(180, 18, 67, '雨雪天气，检查设备引线、线夹主导流接触部位、刀闸主接触部位，对比有无积雪融化、水蒸气现象\r\n'),
(181, 18, 67, '以上检查，若需要鉴定，应使用测温仪对设备进行检测\r\n'),
(182, 18, 67, '无挂落异物\r\n'),
(183, 18, 68, '应完好、清洁、无破损、放电痕迹\r\n'),
(184, 18, 69, '防误闭锁装置锁具完好，闭锁可靠\r\n'),
(185, 18, 69, '机械联锁装置应完整可靠\r\n'),
(186, 18, 69, '机构箱门关闭严密\r\n'),
(187, 18, 70, '无弯曲变形、松动、锈蚀\r\n'),
(188, 18, 71, '正常在“分”位，助力连杆无断股，闭锁良好\r\n'),
(189, 19, 72, '开关柜外观检查无异常，柜门严密，柜体无倾斜，油漆无脱落\r\n'),
(190, 19, 72, '带电显示装置清晰，指示正确\r\n'),
(191, 19, 72, '检查开关柜温度正常，必要时应使用测温仪对设备进行检测\r\n'),
(192, 19, 73, '套管清洁，无破损、裂纹、放电痕迹\r\n'),
(193, 19, 73, '本体无渗漏油，油标指示油位不超过油位线正常范围，油色为淡黄色透明\r\n'),
(194, 19, 73, '内部无任何异常声音、气味\r\n'),
(195, 19, 74, '外壳接地良好\r\n'),
(196, 19, 75, '引接线及接地部位接触良好\r\n'),
(197, 19, 75, '外观良好，无破损\r\n'),
(198, 20, 76, '房屋四壁及房顶应无裂纹、渗水、漏雨现象\r\n'),
(199, 20, 76, '房顶及四壁粉刷物应无脱落现象\r\n'),
(200, 20, 76, '门窗应关闭严密、牢固。无变形，开启灵活。窗户玻璃齐全无破损\r\n'),
(201, 20, 77, '室内光线充足、通风良好\r\n'),
(202, 20, 77, '照明设备齐全，灯具完好，满足工作需要\r\n'),
(203, 20, 77, '事故照明灯具完好，试开正常\r\n'),
(204, 20, 77, '室内温度应控制在10-26℃，否则应开启空调\r\n'),
(205, 20, 78, '消防器材数量和存放符合要求\r\n'),
(206, 20, 78, '消防器材检验不超期，合格证齐全在有效期\r\n'),
(207, 20, 79, '主控室的门口防鼠挡板齐全严密\r\n'),
(208, 20, 79, '电缆孔洞应封堵严密\r\n'),
(209, 20, 80, '外观清洁\r\n'),
(210, 20, 80, '试开正常\r\n'),
(211, 21, 81, '试验事故及预告音响信号正常，复归正常\r\n'),
(212, 21, 82, '检查远动屏上无异常告警信号\r\n'),
(213, 21, 82, '装置显示正常、内部无异常响声\r\n'),
(214, 21, 83, '监控微机UPS电源正常\r\n'),
(215, 21, 83, '正常时应无任何异常信号\r\n'),
(216, 21, 83, '无任何报警信号及相关提示\r\n'),
(217, 22, 84, '测控屏上开关位置指示与实际位置一致\r\n'),
(218, 22, 84, '保护压板投、退位置正确，压接牢固\r\n'),
(219, 22, 84, '无异常告警信号\r\n'),
(220, 22, 84, '二次线无松脱、发热变色现象，电缆孔洞封堵严密\r\n'),
(221, 23, 85, '功能正常、无告警信息\r\n'),
(222, 23, 85, '系统中的设备状态必须与现场运行设备的状态保持一致\r\n'),
(223, 24, 86, '控制母线电压应保持在198～242V\r\n'),
(224, 24, 86, '合闸母线电压应保持在218～266V\r\n'),
(225, 24, 87, '抽测蓄电池电压，一般应保持在2.2～2.4（2.2V电池）\r\n'),
(226, 24, 87, '瓶体密闭良好，无渗漏液现象\r\n'),
(227, 24, 87, '瓶体完整，无倾斜变形，表面清洁，附件齐全良好\r\n'),
(228, 24, 87, '各连接部位接触良好，无松动、腐蚀现象\r\n'),
(229, 24, 88, '直流控制（信号）、合闸回路正常应按规定运行\r\n'),
(230, 24, 88, '各回路空开配置应符合该回路负载要求\r\n'),
(231, 24, 88, '各刀闸、保险及接头均应接触良好，无发热变色现象\r\n'),
(232, 25, 89, '保护装置运行正常，无异常告警信号，无异常声音及气味\r\n'),
(233, 25, 89, '装置外壳无破损，接点无抖动，内部无异常响声\r\n'),
(234, 25, 89, '正常运行时“运行”指示灯灯亮，开关合位灯亮，其它信号灯均不亮\r\n'),
(235, 25, 89, '标示清晰，准确\r\n'),
(236, 25, 89, '保护压板投、退位置正确，压接牢固\r\n'),
(237, 25, 89, '二次线无松脱、发热变色现象，电缆孔洞封堵严密\r\n'),
(238, 25, 90, '装置运行正常，无异常告警信号，无异常声音及气味\r\n'),
(239, 25, 90, '外壳无破损，接点无抖动，内部无异常响声\r\n'),
(240, 25, 90, '标示清晰，准确\r\n'),
(241, 25, 90, '保护压板投、退位置正确，压接牢固\r\n'),
(242, 25, 90, '二次线无松脱、发热变色现象，电缆孔洞封堵严密\r\n'),
(243, 25, 90, '各交直流开关均投入正常\r\n'),
(244, 26, 91, '装置屏上各交、直流开关均投入正常\r\n'),
(245, 26, 91, '装置投入运行时指示灯“运行”灯亮\r\n'),
(246, 26, 91, '装置面板I母或II母电压显示正常\r\n'),
(247, 26, 91, '无异常报警信号\r\n'),
(248, 26, 91, '保护压板投、退位置正确，压接牢固，编号清晰准确\r\n'),
(249, 26, 91, '二次线无松脱、发热变色现象，电缆孔洞封堵严密');

-- --------------------------------------------------------

--
-- Table structure for table `xsnr`
--

CREATE TABLE IF NOT EXISTS `xsnr` (
  `id` int(11) NOT NULL,
  `sblx_id` int(11) NOT NULL,
  `nr` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `xsnr_sblx` (`sblx_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Dumping data for table `xsnr`
--

INSERT INTO `xsnr` (`id`, `sblx_id`, `nr`) VALUES
(1, 1, '开关柜\r\n'),
(2, 1, '电流互感器\r\n'),
(3, 1, '断路器（开关）\r\n'),
(4, 1, '电缆出线\r\n'),
(5, 1, '保护及测控部分\r\n'),
(6, 1, '隔离开关\r\n'),
(7, 2, '房屋及防火检查\r\n'),
(8, 2, '通风照明\r\n'),
(9, 2, '电缆沟及防鼠检查\r\n'),
(10, 3, '开关柜\r\n'),
(11, 3, '母电压互感器\r\n'),
(12, 3, '外壳接地\r\n'),
(13, 4, '母线排(封闭)\r\n'),
(14, 4, '穿墙套管\r\n'),
(15, 4, '开关柜\r\n'),
(16, 4, '电流互感器\r\n'),
(17, 4, '断路器（开关）\r\n'),
(18, 4, '线路避雷器\r\n'),
(19, 5, '开关柜\r\n'),
(20, 5, '电流互感器\r\n'),
(21, 5, '断路器（开关）\r\n'),
(22, 5, '电缆出线\r\n'),
(23, 5, '保护及测控部分\r\n'),
(24, 5, '线路避雷器\r\n'),
(25, 6, '开关柜\r\n'),
(26, 6, '高压保险\r\n'),
(27, 6, '电缆出线\r\n'),
(28, 7, '声音\r\n'),
(29, 7, '站用变本体\r\n'),
(30, 8, '上层油温\r\n'),
(31, 8, '油位、油色\r\n'),
(32, 8, '变压器本体、附件及各连接处无渗漏油\r\n'),
(33, 8, '变压器变本体及瓦斯继电器\r\n'),
(34, 8, '运行中的声音\r\n'),
(35, 8, '压力释放装置\r\n'),
(36, 8, '呼吸器\r\n'),
(37, 9, '油位\r\n'),
(38, 9, '油色\r\n'),
(39, 9, '套管\r\n'),
(40, 9, '法兰\r\n'),
(41, 10, '主导流接触部位是否接触良好、有无发热现象\r\n'),
(42, 10, '引线有无断股、线夹有无损伤、接触是否良好\r\n'),
(43, 11, '散热器\r\n'),
(44, 12, '中性点接地刀闸位置\r\n'),
(45, 12, '中性点电流互感器\r\n'),
(46, 12, '接地装置\r\n'),
(47, 12, '避雷器\r\n'),
(48, 13, '箱体、箱门\r\n'),
(49, 13, '内部\r\n'),
(50, 14, '母线排(封闭)\r\n'),
(51, 14, '开关柜\r\n'),
(52, 14, '电流互感器\r\n'),
(53, 14, '断路器（开关）\r\n'),
(54, 14, '线路避雷器\r\n'),
(55, 15, '母线及接头\r\n'),
(56, 15, '母线瓷瓶\r\n'),
(57, 15, '构架\r\n'),
(58, 16, '瓷套\r\n'),
(59, 16, '本体\r\n'),
(60, 16, '油位、油色\r\n'),
(61, 16, '引线及接触部位\r\n'),
(62, 16, '膨胀器\r\n'),
(63, 17, '瓷质部分\r\n'),
(64, 17, '本体\r\n'),
(65, 17, '避雷器引线\r\n'),
(66, 17, '接地\r\n'),
(67, 18, '触头、引线、线夹等主接触部位\r\n'),
(68, 18, '瓷质部分\r\n'),
(69, 18, '操作机构\r\n'),
(70, 18, '传动机构连杆\r\n'),
(71, 18, '接地刀闸\r\n'),
(72, 19, '开关柜\r\n'),
(73, 19, '母电压互感器(停电后)\r\n'),
(74, 19, '外壳接地\r\n'),
(75, 19, '线路避雷器\r\n'),
(76, 20, '房屋检查\r\n'),
(77, 20, '室内环境\r\n'),
(78, 20, '消防器材\r\n'),
(79, 20, '防鼠措施\r\n'),
(80, 20, '空调\r\n'),
(81, 21, '音响试验\r\n'),
(82, 21, '综合采集装置检查\r\n'),
(83, 21, '系统监控微机\r\n'),
(84, 22, '测控屏检查\r\n'),
(85, 23, '微机五防、后台监控机\r\n'),
(86, 24, '测控部分检查\r\n'),
(87, 24, '蓄电池检查\r\n'),
(88, 24, '直流网络检查\r\n'),
(89, 25, 'CSC-326A保护装置\r\n'),
(90, 25, '操作箱\r\n'),
(91, 26, '35kV、10kV RCS-9651C备自投装置\r\n');
