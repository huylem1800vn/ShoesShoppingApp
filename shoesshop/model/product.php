<?php 
    class product{
        private $conn;

        public $productID;
        public $name;
        public $price;
        public $promotionalPrice;
        public $description;
        public $perRed;
        public $image1;
        public $image2;
        public $image3;
        public $image4;
        public $image5;
        public $brandID;
        public $brandName;
        public $categoryID;
        public $categoryName;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT `product`.*, `A01`.name AS brandName, `A02`.name AS categoryName
            FROM `product`
            LEFT JOIN `brand` AS A01 ON `product`.brandID = `A01`.brandID
            LEFT JOIN `categories` AS A02  ON `product`.categoryID = `A02`.categoryID";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT `product`.*, `A01`.name AS brandName, `A02`.name AS categoryName
            FROM `product`
            LEFT JOIN `brand` AS A01 ON `product`.brandID = `A01`.brandID
            LEFT JOIN `categories` AS A02  ON `product`.categoryID = `A02`.categoryID
            WHERE `product`.productID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->productID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->name = $row['name'];
            $this->price = $row['price'];
            $this->promotionalPrice = $row['promotionalPrice'];
            $this->description = $row['description'];
            $this->perRed = $row['perRed'];
            $this->image1 = $row['image1'];
            $this->image2 = $row['image2'];
            $this->image3 = $row['image3'];
            $this->image4 = $row['image4'];
            $this->image5 = $row['image5'];
            $this->brandID = $row['brandID'];
            $this->brandName = $row['brandName'];
            $this->categoryID = $row['categoryID'];
            $this->categoryName = $row['categoryName'];
        }

        public function create(){
            $query = "INSERT INTO `product` SET `name`=:`name`, `price`=:`price`, `promotionalPrice`=:`promotionalPrice`, `description`=:`description`, `perRed`=:`perRed`, `image1`=:`image1`, `image2`=:`image2`, `image3`=:`image3`, `image4`=:`image4`, `image5`=:`image5`, `brandID`=:`brandID`, `categoryID`=:`categoryID`";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->name = htmlspecialchars(strip_tags($this->name));
            $this->price = htmlspecialchars(strip_tags($this->price));
            $this->promotionalPrice = htmlspecialchars(strip_tags($this->promotionalPrice));
            $this->description = htmlspecialchars(strip_tags($this->description));
            $this->perRed = htmlspecialchars(strip_tags($this->perRed));
            $this->image1 = htmlspecialchars(strip_tags($this->image1));
            $this->image2 = htmlspecialchars(strip_tags($this->image2));
            $this->image3 = htmlspecialchars(strip_tags($this->image3));
            $this->image4 = htmlspecialchars(strip_tags($this->image4));
            $this->image5 = htmlspecialchars(strip_tags($this->image5));
            $this->brandID = htmlspecialchars(strip_tags($this->brandID));
            $this->categoryID = htmlspecialchars(strip_tags($this->categoryID));
            //bind data
            $statement->bindParam(':name', $this->name);
            $statement->bindParam(':price', $this->price);
            $statement->bindParam(':promotionalPrice', $this->promotionalPrice);
            $statement->bindParam(':description', $this->description);
            $statement->bindParam(':perRed', $this->perRed);
            $statement->bindParam(':image1', $this->image1);
            $statement->bindParam(':image2', $this->image2);
            $statement->bindParam(':image3', $this->image3);
            $statement->bindParam(':image4', $this->image4);
            $statement->bindParam(':image5', $this->image5);
            $statement->bindParam(':brandID', $this->brandID);
            $statement->bindParam(':categoryID', $this->categoryID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function update(){
            $query = "UPDATE `product` SET `name`=:`name`, `price`=:`price`, `promotionalPrice`=:`promotionalPrice`, `description`=:`description`, `perRed`=:`perRed`, `image1`=:`image1`, `image2`=:`image2`, `image3`=:`image3`, `image4`=:`image4`, `image5`=:`image5`, `brandID`=:`brandID`, `categoryID`=:`categoryID` WHERE productID=:productID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->productID = htmlspecialchars(strip_tags($this->productID));
            $this->name = htmlspecialchars(strip_tags($this->name));
            $this->price = htmlspecialchars(strip_tags($this->price));
            $this->promotionalPrice = htmlspecialchars(strip_tags($this->promotionalPrice));
            $this->description = htmlspecialchars(strip_tags($this->description));
            $this->perRed = htmlspecialchars(strip_tags($this->perRed));
            $this->image1 = htmlspecialchars(strip_tags($this->image1));
            $this->image2 = htmlspecialchars(strip_tags($this->image2));
            $this->image3 = htmlspecialchars(strip_tags($this->image3));
            $this->image4 = htmlspecialchars(strip_tags($this->image4));
            $this->image5 = htmlspecialchars(strip_tags($this->image5));
            $this->brandID = htmlspecialchars(strip_tags($this->brandID));
            $this->categoryID = htmlspecialchars(strip_tags($this->categoryID));
            //bind data
            $statement->bindParam(':productID', $this->productID);
            $statement->bindParam(':name', $this->name);
            $statement->bindParam(':price', $this->price);
            $statement->bindParam(':promotionalPrice', $this->promotionalPrice);
            $statement->bindParam(':description', $this->description);
            $statement->bindParam(':perRed', $this->perRed);
            $statement->bindParam(':image1', $this->image1);
            $statement->bindParam(':image2', $this->image2);
            $statement->bindParam(':image3', $this->image3);
            $statement->bindParam(':image4', $this->image4);
            $statement->bindParam(':image5', $this->image5);
            $statement->bindParam(':brandID', $this->brandID);
            $statement->bindParam(':categoryID', $this->categoryID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function delete(){
            $query = "DELETE FROM `product` WHERE productID=:productID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->productID = htmlspecialchars(strip_tags($this->productID));
            //bind data
            $statement->bindParam(':productID', $this->productID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>