<?php 
    class orderstatus{
        private $conn;

        public $orderStatusID;
        public $name;
        public $nameE;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `orderstatus`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT * FROM `orderstatus` WHERE orderStatusID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->orderStatusID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->name = $row['name'];
            $this->nameE = $row['nameE'];
        }

        public function update(){
            $query = "UPDATE `orderstatus` SET `name`=:`name`, `nameE`=:`nameE` WHERE orderStatusID=:orderStatusID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->orderStatusID = htmlspecialchars(strip_tags($this->orderStatusID));
            $this->name = htmlspecialchars(strip_tags($this->name));
            $this->nameE = htmlspecialchars(strip_tags($this->nameE));
            //bind data
            $statement->bindParam(':orderStatusID', $this->orderStatusID);
            $statement->bindParam(':name', $this->name);
            $statement->bindParam(':nameE', $this->nameE);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function delete(){
            $query = "DELETE FROM `orderstatus` WHERE orderStatusID=:orderStatusID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->orderStatusID = htmlspecialchars(strip_tags($this->orderStatusID));
            //bind data
            $statement->bindParam(':orderStatusID', $this->orderStatusID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>