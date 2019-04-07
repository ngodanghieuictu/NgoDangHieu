<?php
	require 'connect.php';
	 // $name_user = $_POST['nameuser'];
	 // $pass_user = $_POST['passuser'];
	 $name_user = $_GET['name'];
	 $pass_user = $_GET['pass'];
	// $name_user = 'admin';
	// $pass_user ='admin';

	$query = "SELECT * FROM tbl_user WHERE namelogin_user='$name_user' AND pass_user='$pass_user'";
	$data = mysqli_query($connect,$query);

	class user{
		function user($id,$tdn,$mk,$level,$ht){
			$this->ID    =$id;
			$this->TDN  =$tdn;
			$this->MK 	  =$mk;
			$this->LEVEL =$level;
			$this->HT	  =$ht;
			
		}
	}
	$mang_user = array();

	
	

	while($row =mysqli_fetch_assoc($data)){
		array_push($mang_user, new user($row['id'],$row['namelogin_user'],$row['pass_user'],$row['level'],$row['name_user']));
	}
			
	echo json_encode($mang_user);
	// if($tdn!=null){
	// 	echo "success";
	// }else{
	// 	echo "erro";
	// }

?>