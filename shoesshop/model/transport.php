<?php 
    class transport{
        private $conn;

        public $transportID;
        public $image;
        public $completeDate;
        public $orderID ;
        public $shipperID ;


        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `transport`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT *, `A01`.name AS shipperName FROM `transport`
            LEFT JOIN `account` AS A01 ON `A01`.accountID=`transport`.shipperID
            WHERE transportID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->transportID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->image = $row['image'];
            $this->completeDate = $row['completeDate'];
            $this->orderID = $row['orderID'];
            $this->shipperID = $row['shipperID'];
        }

        public function create(){
            $query = "INSERT INTO `transport` SET `image`=:`image`, `completeDate`=:`completeDate`, `orderID`=:`orderID`, `shipperID`=:`shipperID`";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->image = htmlspecialchars(strip_tags($this->image));
            $this->completeDate = htmlspecialchars(strip_tags($this->completeDate));
            $this->orderID = htmlspecialchars(strip_tags($this->orderID));
            $this->shipperID = htmlspecialchars(strip_tags($this->shipperID));
            //bind data
            $statement->bindParam(':image', $this->image);
            $statement->bindParam(':completeDate', $this->completeDate);
            $statement->bindParam(':orderID', $this->orderID);
            $statement->bindParam(':shipperID', $this->shipperID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>