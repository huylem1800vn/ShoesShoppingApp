<?php 
    class order{
        private $conn;

        public $orderID;
        public $customerName;
        public $customerAddress;
        public $customerPhone;
        public $createDate;
        public $orderDate;
        public $totalAmount;
        public $deliveryDate;
        public $createUser;
        public $confirmUser;
        public $shipper;
        public $orderStatus;
        public $orderStatusName;
        public $shipperName;
        public $confirmUserName;
        public $createUserName;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT *, A01.name AS createUserName, A02.name AS confirmUserName, A03.name AS shipperName, A04.name AS orderStatusName FROM `order`
            LEFT JOIN `account` AS A01 ON `A01`.accountID=`order`.createUser
            LEFT JOIN `account` AS A02 ON `A02`.accountID=`order`.confirmUser
            LEFT JOIN `account` AS A03 ON `A03`.accountID=`order`.shipper
            LEFT JOIN `orderStatus` AS A04 ON `A04`.orderStatusID=`order`.orderStatus
            ";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT *, A01.name AS createUserName, A02.name AS confirmUserName, A03.name AS shipperName, A04.name AS orderStatusName FROM `order`
            LEFT JOIN `account` AS A01 ON `A01`.accountID=`order`.createUser
            LEFT JOIN `account` AS A02 ON `A02`.accountID=`order`.confirmUser
            LEFT JOIN `account` AS A03 ON `A03`.accountID=`order`.shipper
            LEFT JOIN `orderStatus` AS A04 ON `A04`.orderStatusID=`order`.orderStatus 
            WHERE orderID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->orderID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->customerName = $row['customerName'];
            $this->customerAddress = $row['customerAddress'];
            $this->customerPhone = $row['customerPhone'];
            $this->createDate = $row['createDate'];
            $this->orderDate = $row['orderDate'];
            $this->totalAmount = $row['totalAmount'];
            $this->deliveryDate = $row['deliveryDate'];
            $this->createUser = $row['createUser'];
            $this->confirmUser = $row['confirmUser'];
            $this->shipper = $row['shipper'];
            $this->orderStatus = $row['orderStatus'];
            $this->orderStatusName = $row['orderStatusName'];
            $this->shipperName = $row['shipperName'];
            $this->confirmUserName = $row['confirmUserName'];
            $this->createUserName = $row['createUserName'];
        }

        public function create(){
            $query = "INSERT INTO `order` SET orderID=:orderID, customerName=:customerName, customerAddress=:customerAddress, customerPhone=:customerPhone, createDate=:createDate, orderDate=:orderDate, totalAmount=:totalAmount, deliveryDate=:deliveryDate, createUser=:createUser, confirmUser=:confirmUser, shipper=:shipper, orderStatus=:orderStatus";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->orderID = htmlspecialchars(strip_tags($this->orderID));
            $this->customerName = htmlspecialchars(strip_tags($this->customerName));
            $this->customerAddress = htmlspecialchars(strip_tags($this->customerAddress));
            $this->customerPhone = htmlspecialchars(strip_tags($this->customerPhone));
            $this->createDate = htmlspecialchars(strip_tags($this->createDate));
            $this->orderDate = htmlspecialchars(strip_tags($this->orderDate));
            $this->totalAmount = htmlspecialchars(strip_tags($this->totalAmount));
            $this->deliveryDate = htmlspecialchars(strip_tags($this->deliveryDate));
            $this->createUser = htmlspecialchars(strip_tags($this->createUser));
            $this->confirmUser = htmlspecialchars(strip_tags($this->confirmUser));
            $this->shipper = htmlspecialchars(strip_tags($this->shipper));
            $this->orderStatus = htmlspecialchars(strip_tags($this->orderStatus));
            //bind data
            $statement->bindParam(':orderID', $this->orderID);
            $statement->bindParam(':customerName', $this->customerName);
            $statement->bindParam(':customerAddress', $this->customerAddress);
            $statement->bindParam(':customerPhone', $this->customerPhone);
            $statement->bindParam(':createDate', $this->createDate);
            $statement->bindParam(':orderDate', $this->orderDate);
            $statement->bindParam(':totalAmount', $this->totalAmount);
            $statement->bindParam(':deliveryDate', $this->deliveryDate);
            $statement->bindParam(':createUser', $this->createUser);
            $statement->bindParam(':confirmUser', $this->confirmUser);
            $statement->bindParam(':shipper', $this->shipper);
            $statement->bindParam(':orderStatus', $this->orderStatus);
            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function updateStatus(){
            $query = "UPDATE `order` SET `orderStatus`=:`orderStatus`, `confirmUser`=:`confirmUser`, `shipper`=:`shipper`, `deliveryDate`=:`deliveryDate`, `customerName`=:`customerName`, `customerAddress`=:`customerAddress`, `customerPhone`=:`customerPhone` WHERE orderID=:orderID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->orderID = htmlspecialchars(strip_tags($this->orderID));
            $this->orderStatus = htmlspecialchars(strip_tags($this->orderStatus));
            $this->confirmUser = htmlspecialchars(strip_tags($this->confirmUser));
            $this->shipper = htmlspecialchars(strip_tags($this->shipper));
            $this->deliveryDate = htmlspecialchars(strip_tags($this->deliveryDate));
            $this->customerName = htmlspecialchars(strip_tags($this->customerName));
            $this->customerAddress = htmlspecialchars(strip_tags($this->customerAddress));
            $this->customerPhone = htmlspecialchars(strip_tags($this->customerPhone));
            //bind data
            $statement->bindParam(':orderID', $this->orderID);
            $statement->bindParam(':orderStatus', $this->orderStatus);
            $statement->bindParam(':confirmUser', $this->confirmUser);
            $statement->bindParam(':shipper', $this->shipper);
            $statement->bindParam(':deliveryDate', $this->deliveryDate);
            $statement->bindParam(':customerName', $this->customerName);
            $statement->bindParam(':customerAddress', $this->customerAddress);
            $statement->bindParam(':customerPhone', $this->customerPhone);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>