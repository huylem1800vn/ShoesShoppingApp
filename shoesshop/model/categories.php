<?php 
    class categories{
        private $conn;

        public $categoryID;
        public $name;
        public $nameE;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `categories`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT * FROM `categories` WHERE categoryID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->categoryID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->name = $row['name'];
            $this->nameE = $row['nameE'];
        }

        public function create(){
            $query = "INSERT INTO `categories` SET `name`=:`name`, `nameE`=:`nameE`";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->name = htmlspecialchars(strip_tags($this->name));
            $this->nameE = htmlspecialchars(strip_tags($this->nameE));
            //bind data
            $statement->bindParam(':name', $this->name);
            $statement->bindParam(':nameE', $this->nameE);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function update(){
            $query = "UPDATE `categories` SET `name`=:`name`, `nameE`=:`nameE` WHERE categoryID=:categoryID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->categoryID = htmlspecialchars(strip_tags($this->categoryID));
            $this->name = htmlspecialchars(strip_tags($this->name));
            $this->nameE = htmlspecialchars(strip_tags($this->nameE));
            //bind data
            $statement->bindParam(':categoryID', $this->categoryID);
            $statement->bindParam(':name', $this->name);
            $statement->bindParam(':nameE', $this->nameE);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function delete(){
            $query = "DELETE FROM `categories` WHERE categoryID=:categoryID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->categoryID = htmlspecialchars(strip_tags($this->categoryID));
            //bind data
            $statement->bindParam(':categoryID', $this->categoryID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>