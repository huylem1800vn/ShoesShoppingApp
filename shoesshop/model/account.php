<?php 
    class account{
        private $conn;

        public $accountID;
        public $userName;
        public $password;
        public $name;
        public $phone;
        public $address;
        public $gmail;
        public $roleID;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `account`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT * FROM `account` WHERE accountID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->accountID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->accountID = $row['accountID'];
            $this->userName = $row['userName'];
            $this->password = $row['password'];
            $this->name = $row['name'];
            $this->phone = $row['phone'];
            $this->address = $row['address'];
            $this->gmail = $row['gmail'];
            $this->roleID = $row['roleID'];
        }

        public function create(){
            $query = "INSERT INTO `account` SET userName=:userName, `password`=:`password`, `name`=:`name`, phone=:phone, `address`=:`address`, gmail=:gmail, roleID=:roleID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->userName = htmlspecialchars(strip_tags($this->userName));
            $this->password = htmlspecialchars(strip_tags($this->password));
            $this->name = htmlspecialchars(strip_tags($this->name));
            $this->phone = htmlspecialchars(strip_tags($this->phone));
            $this->address = htmlspecialchars(strip_tags($this->address));
            $this->gmail = htmlspecialchars(strip_tags($this->gmail));
            $this->roleID = htmlspecialchars(strip_tags($this->roleID));
            //bind data
            $statement->bindParam(':userName', $this->userName);
            $statement->bindParam(':password', $this->password);
            $statement->bindParam(':name', $this->name);
            $statement->bindParam(':phone', $this->phone);
            $statement->bindParam(':address', $this->address);
            $statement->bindParam(':gmail', $this->gmail);
            $statement->bindParam(':roleID', $this->roleID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function update(){
            $query = "UPDATE `account` SET `name`=:`name`, phone=:phone, `address`=:`address`, gmail=:gmail, roleID=:roleID WHERE accountID=:accountID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->accountID = htmlspecialchars(strip_tags($this->accountID));
            $this->name = htmlspecialchars(strip_tags($this->name));
            $this->phone = htmlspecialchars(strip_tags($this->phone));
            $this->address = htmlspecialchars(strip_tags($this->address));
            $this->gmail = htmlspecialchars(strip_tags($this->gmail));
            $this->roleID = htmlspecialchars(strip_tags($this->roleID));
            //bind data
            $statement->bindParam(':accountID', $this->accountID);
            $statement->bindParam(':name', $this->name);
            $statement->bindParam(':phone', $this->phone);
            $statement->bindParam(':address', $this->address);
            $statement->bindParam(':gmail', $this->gmail);
            $statement->bindParam(':roleID', $this->roleID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function delete(){
            $query = "DELETE FROM `account` WHERE accountID=:accountID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->accountID = htmlspecialchars(strip_tags($this->accountID));
            //bind data
            $statement->bindParam(':accountID', $this->accountID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>