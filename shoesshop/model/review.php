<?php 
    class review{
        private $conn;

        public $reviewID;
        public $description;
        public $star;
        public $reviewDate;
        public $reviewUser;
        public $productID;
        public $reviewUserName;
        public $productName;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `review`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT *, `A01`.name AS reviewUserName, `A02`.name AS productName FROM `review`
            LEFT JOIN `account` AS A01 ON `A01`.accountID=`review`.reviewUser
            LEFT JOIN `product` AS A02 ON `A02`.productID=`review`.productID
            WHERE reviewID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->reviewID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->description = $row['description'];
            $this->star = $row['star'];
            $this->reviewDate = $row['reviewDate'];
            $this->reviewUser = $row['reviewUser'];
            $this->productID = $row['productID'];
            $this->reviewUserName = $row['reviewUserName'];
            $this->productName = $row['productName'];
        }

        public function create(){
            $query = "INSERT INTO `review` SET `description`=:`description`, `star`=:`star`, `reviewDate`=:`reviewDate`, `reviewUser`=:`reviewUser`, `productID`=:`productID`";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->description = htmlspecialchars(strip_tags($this->description));
            $this->star = htmlspecialchars(strip_tags($this->star));
            $this->reviewDate = htmlspecialchars(strip_tags($this->reviewDate));
            $this->reviewUser = htmlspecialchars(strip_tags($this->reviewUser));
            $this->productID = htmlspecialchars(strip_tags($this->productID));
            //bind data
            $statement->bindParam(':description', $this->description);
            $statement->bindParam(':star', $this->star);
            $statement->bindParam(':reviewDate', $this->reviewDate);
            $statement->bindParam(':reviewUser', $this->reviewUser);
            $statement->bindParam(':productID', $this->productID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }


        public function delete(){
            $query = "DELETE FROM `review` WHERE reviewID=:reviewID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->reviewID = htmlspecialchars(strip_tags($this->reviewID));
            //bind data
            $statement->bindParam(':reviewID', $this->reviewID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>