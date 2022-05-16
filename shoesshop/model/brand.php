<?php 
    class brand{
        private $conn;

        public $id;
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
            $query = "SELECT * FROM `brand` WHERE id=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->id);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->name = $row['name'];
            $this->description = $row['description'];
            $this->image = $row['image'];
        }

    }
?>