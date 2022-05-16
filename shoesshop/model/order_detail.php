<?php 
    class order_detail{
        private $conn;

        public $id;
        public $order_id;
        public $product_id;
        public $size;
        public $quantity;
        public $total_amount;
        public $product_name;
        public $image;
        public $product_price;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `order_detail`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT `order_detail`.*, `product`.name AS product_name, `product`.image_1 AS image, `product`.promotional_price AS product_price
            FROM `order_detail` 
            LEFT JOIN `product`
            ON `order_detail`.product_id = `product`.id
            WHERE order_id=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->order_id);
            $statement->execute();
            return $statement;
        }

        public function create(){
            $query = "INSERT INTO `order_detail` SET order_id=:order_id, product_id=:product_id, size=:size, quantity=:quantity, total_amount=:total_amount";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->order_id = htmlspecialchars(strip_tags($this->order_id));
            $this->product_id = htmlspecialchars(strip_tags($this->product_id));
            $this->size = htmlspecialchars(strip_tags($this->size));
            $this->quantity = htmlspecialchars(strip_tags($this->quantity));
            $this->total_amount = htmlspecialchars(strip_tags($this->total_amount));
            //bind data
            $statement->bindParam(':order_id', $this->order_id);
            $statement->bindParam(':product_id', $this->product_id);
            $statement->bindParam(':size', $this->size);
            $statement->bindParam(':quantity', $this->quantity);
            $statement->bindParam(':total_amount', $this->total_amount);
            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }
    }
?>