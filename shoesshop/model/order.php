<?php 
    class order{
        private $conn;

        public $id;
        public $customer_name;
        public $customer_address;
        public $customer_phone;
        public $total_amount;
        public $order_date;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT * FROM `order`";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT * FROM `order` WHERE id=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->id);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->customer_name = $row['customer_name'];
            $this->total_amount = $row['total_amount'];
            $this->order_date = $row['order_date'];
        }

        public function create(){
            $query = "INSERT INTO `order` SET id=:id, customer_name=:customer_name, customer_address=:customer_address, customer_phone=:customer_phone, total_amount=:total_amount, order_date=:order_date";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->id = htmlspecialchars(strip_tags($this->id));
            $this->customer_name = htmlspecialchars(strip_tags($this->customer_name));
            $this->customer_phone = htmlspecialchars(strip_tags($this->customer_phone));
            $this->customer_address = htmlspecialchars(strip_tags($this->customer_address));
            $this->total_amount = htmlspecialchars(strip_tags($this->total_amount));
            $this->order_date = htmlspecialchars(strip_tags($this->order_date));
            //bind data
            $statement->bindParam(':id', $this->id);
            $statement->bindParam(':customer_name', $this->customer_name);
            $statement->bindParam(':customer_phone', $this->customer_phone);
            $statement->bindParam(':customer_address', $this->customer_address);
            $statement->bindParam(':total_amount', $this->total_amount);
            $statement->bindParam(':order_date', $this->order_date);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>