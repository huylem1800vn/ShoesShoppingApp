<?php 
    class message{
        private $conn;

        public $messageID;
        public $text;
        public $sendUser;
        public $receiveUser;
        public $messageDate;
        public $sendUserName;
        public $receiveUserName;

        //connect database
        public function __construct($db){
            $this->conn = $db;
        }

        //read data
        public function read(){
            $query = "SELECT *, `A01`.`name` AS sendUserName, `A02`.`name` AS receiveUserName  FROM `message`
            LEFT JOIN `account` AS A01 ON `account`.accountID=`message`.sendUser
            LEFT JOIN `account` AS A02 ON `account`.accountID=`message`.receiveUser";
            $statement = $this->conn->prepare($query);
            $statement->execute();
            return $statement;
        }

        public function show(){
            $query = "SELECT *, `account`.`name` AS sendUserName, `account`.`name` AS receiveUserName  FROM `message`
            LEFT JOIN `account` AS A01 ON `account`.accountID=`message`.sendUser
            LEFT JOIN `account` AS A02 ON `account`.accountID=`message`.receiveUser
             WHERE messageID=?";
            $statement = $this->conn->prepare($query);
            $statement->bindParam(1, $this->messageID);
            $statement->execute();

            $row = $statement->fetch(PDO::FETCH_ASSOC);

            $this->text = $row['text'];
            $this->sendUser = $row['sendUser'];
            $this->receiveUser = $row['receiveUser'];
            $this->messageDate = $row['messageDate'];
            $this->sendUserName = $row['sendUserName'];
            $this->receiveUserName = $row['receiveUserName'];
        }

        public function create(){
            $query = "INSERT INTO `message` SET `text`=:`text`, `sendUser`=:`sendUser`, `receiveUser`=:`receiveUser`, `messageDate`=:`messageDate`";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->text = htmlspecialchars(strip_tags($this->text));
            $this->sendUser = htmlspecialchars(strip_tags($this->sendUser));
            $this->receiveUser = htmlspecialchars(strip_tags($this->receiveUser));
            $this->messageDate = htmlspecialchars(strip_tags($this->messageDate));
            //bind data
            $statement->bindParam(':text', $this->text);
            $statement->bindParam(':sendUser', $this->sendUser);
            $statement->bindParam(':receiveUser', $this->receiveUser);
            $statement->bindParam(':messageDate', $this->messageDate);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

        public function delete(){
            $query = "DELETE FROM `message` WHERE messageID=:messageID";

            $statement = $this->conn->prepare($query);
            //clean data
            $this->messageID = htmlspecialchars(strip_tags($this->messageID));
            //bind data
            $statement->bindParam(':messageID', $this->messageID);

            if($statement->execute()){
                return true;
            }
            printf("Error %s.\n" .$statement->error);
            return false;
        }

    }
?>