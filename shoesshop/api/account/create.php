<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: POST');
    header('Access-Control-Allow-Headers:Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

    include_once('../../config/db.php');
    include_once('../../model/account.php');

    $db = new db();
    $connect = $db->connect();
    $account = new account($connect);

    $data = json_decode(file_get_contents("php://input"));

    $account->userName = $data->userName;
    $account->password = $data->password;
    $account->name = $data->name;
    $account->phone = $data->phone;
    $account->address = $data->address;
    $account->gmail = $data->gmail;
    $account->roleID = $data->roleID;

    if($account->create()){
        echo json_encode(array('message'=>'Account Created'));
    } else {
        echo json_encode(array('message'=>'Account Not Created'));
    }
?>