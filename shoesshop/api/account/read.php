<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/account.php');

    $db = new db();
    $connect = $db->connect();
    $account = new account($connect);
    $read = $account->read();

    $num = $read->rowCount();

    if($num > 0){
        $array_account = [];

        while($row = $read->fetch(PDO::FETCH_ASSOC)){
            
            extract($row);

            $account_item = array(
                'accountID' => $accountID,
                'userName' => $userName,
                'password' => $password,
                'name' => $name,
                'phone' => $phone,
                'address' => $address,
                'gmail' => $gmail,
                'roleID' => $roleID,
            );
            array_push($array_account, $account_item);
        }
        echo json_encode($array_account);
    }
?>