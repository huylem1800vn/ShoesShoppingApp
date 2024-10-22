<?php 
    class userrole{
        private $conn;

        public $roleID;
        public $name;
        public $description;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `userrole`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT * FROM `userrole` WHERE roleID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->roleID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->name = $row['name'];
            $this->description = $row['description'];
        }
    }
?>