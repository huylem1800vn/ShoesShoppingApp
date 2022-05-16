<?php 
    class product{
        private $conn;

        public $id;
        public $name;
        public $price;
        public $promotional_price;
        public $description;
        public $per_red;
        public $image_1;
        public $image_2;
        public $image_3;
        public $brand_id;
        public $brand_name;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `product`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT `product`.*, `brand`.name AS brand_name
            FROM `product`
            LEFT JOIN `brand`
            ON `product`.brand_id = `brand`.id
            WHERE `product`.id=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->id);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->name = $row['name'];
            $this->price = $row['price'];
            $this->promotional_price = $row['promotional_price'];
            $this->description = $row['description'];
            $this->per_red = $row['per_red'];
            $this->image_1 = $row['image_1'];
            $this->image_2 = $row['image_2'];
            $this->image_3 = $row['image_3'];
            $this->brand_id = $row['brand_id'];
            $this->brand_name = $row['brand_name'];
        }

    }
?>