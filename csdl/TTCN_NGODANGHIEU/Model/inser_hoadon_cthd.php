<?php

require 'connect.php';
$data=mysqli_query($connect,"SELECT Max(id) as id FROM tbl_hoadon");
	$mang=[];
	while ($row=mysqli_fetch_assoc($data)) {
	 	$mang[]=$row;
	 } 
	 $id_hoadon= $mang[0]['id']+1;
	// tenMonAn,donvi;id,sl,gia,thanhtien;
	$data_hoadon = $_POST['MangOrder'];
	$DATE=date("Y-m-d");
	$hihi=json_decode($data_hoadon,true) ;
	$tong =0;
	foreach ($hihi as $key) {
		mysqli_query($connect,"INSERT INTO tbl_chitiethoadon(id_hoadon,id_monan,sl) VALUES('".$id_hoadon."','".$key['id']."','".$key['sl']."')");
		$tong+=$key['sl']*$key['gia'];
	}

	$ktra=mysqli_query($connect,"INSERT INTO tbl_hoadon(id_user,date_hoadon,tongtien) VALUES('".$hihi[0]['id_usser']."','".$DATE."','".$tong."')");

	if($ktra){

		echo"success";
	}else{
		echo"error";
	}
	

?>