<?php
	require 'connect.php';

	if(isset($_GET['loaisp'])){
		$a = $_GET['loaisp'];
		$query = "SELECT * FROM tbl_sp WHERE loai_sp='$a' AND sl_sp!=0";
	}else{
		$query = "SELECT * FROM tbl_sp WHERE sl_sp!=0";
	}
	
	$data = mysqli_query($connect,$query);

	class SanPham{
		function SanPham($id,$tensp,$slsp,$ngaynhap,$nsx,$iduser,$img,$loaisp){
			$this->ID     =$id;
			$this->TSP    =$tensp;
			$this->SLSP   =$slsp;
			$this->NP     =$ngaynhap;
			$this->NSX	  =$nsx;
			$this->IDU	  =$iduser;
			$this->IMG	  =$img;
			$this->LSP	  =$loaisp;
			
		}
	}
	$mang_sp = array();

	
	

	while($row =mysqli_fetch_assoc($data)){
		array_push($mang_sp, new SanPham($row['id'],$row['ten_sp'],$row['sl_sp'],$row['ngaynhap'],$row['nhasx'],$row['id_user'],$row['img'],$row['loai_sp']));
	}
			
	echo json_encode($mang_sp);
	// if($tdn!=null){
	// 	echo "success";
	// }else{
	// 	echo "erro";
	// }

?>