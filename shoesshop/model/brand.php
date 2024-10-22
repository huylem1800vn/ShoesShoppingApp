<?php 
    class brand{
        private $conn;

        public $brandID;
        public $name;
        public $description;
        public $image;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `brand`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT * FROM `brand` WHERE brandID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->brandID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->name = $row['name'];
            $this->description = $row['description'];
            $this->image = $row['image'];
        }

        public function create(){
            $query = "INSERT INTO `brand` SET `name`=:`name`, `description`=:`description`, `image`=:`image`";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->name = htmlspecialchars(strip_tags($this->name));
            $this->description = htmlspecialchars(strip_tags($this->description));
            $this->image = htmlspecialchars(strip_tags($this->image));
            //bind data
            $statement->bindParam(':name', $this->name);
            $statement->bindParam(':description', $this->description);
            $statement->bindParam(':image', $this->image);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function update(){
            $query = "UPDATE `brand` SET `name`=:`name`, `description`=:`description`, `image`=:`image` WHERE brandID=:brandID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->brandID = htmlspecialchars(strip_tags($this->brandID));
            $this->name = htmlspecialchars(strip_tags($this->name));
            $this->description = htmlspecialchars(strip_tags($this->description));
            $this->image = htmlspecialchars(strip_tags($this->image));
            //bind data
            $statement->bindParam(':brandID', $this->brandID);
            $statement->bindParam(':name', $this->name);
            $statement->bindParam(':description', $this->description);
            $statement->bindParam(':image', $this->image);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function delete(){
            $query = "DELETE FROM `brand` WHERE brandID=:brandID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->brandID = htmlspecialchars(strip_tags($this->brandID));
            //bind data
            $statement->bindParam(':brandID', $this->brandID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>