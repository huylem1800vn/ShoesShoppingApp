<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/account.php');

    $db = new db();
    $connect = $db->connect();
    $account = new account($connect);
    $account->accountID = isset($_GET['accountID']) ? $_GET['accountID'] : die();

    $account->show();

    $account_item = array(
        'accountID' => $account->accountID,
        'userName' => $account->userName,
        'password' => $account->password,
        'name' => $account->name,
        'phone' => $account->phone,
        'address' => $account->address,
        'gmail' => $account->gmail,
        'roleID ' => $account->roleID,
    );

    echo json_encode($account_item);

?>