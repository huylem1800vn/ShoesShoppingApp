<?php 
    class orderdetail{
        private $conn;

        public $orderdetailID;
        public $orderID;
        public $productID;
        public $size;
        public $quantity;
        public $totalAmount;
        public $productName;
        public $image;
        public $productPrice;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `orderdetail`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT `orderdetail`.*, `product`.name AS productName, `product`.image1 AS image, `product`.promotionalPrice AS productPrice
            FROM `orderdetail` 
            LEFT JOIN `product`
            ON `orderdetail`.productID = `product`.productID
            WHERE orderID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->orderID);
            $statement->execute();
            return $statement;
        }

        public function create(){
            $query = "INSERT INTO `orderdetail` SET orderID=:orderID, productID=:productID, size=:size, quantity=:quantity, totalAmount=:totalAmount";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->orderID = htmlspecialchars(strip_tags($this->orderID));
            $this->productID = htmlspecialchars(strip_tags($this->productID));
            $this->size = htmlspecialchars(strip_tags($this->size));
            $this->quantity = htmlspecialchars(strip_tags($this->quantity));
            $this->totalAmount = htmlspecialchars(strip_tags($this->totalAmount));
            //bind data
            $statement->bindParam(':orderID', $this->orderID);
            $statement->bindParam(':productID', $this->productID);
            $statement->bindParam(':size', $this->size);
            $statement->bindParam(':quantity', $this->quantity);
            $statement->bindParam(':totalAmount', $this->totalAmount);
            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }
    }
?>