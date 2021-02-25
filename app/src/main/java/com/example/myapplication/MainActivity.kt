package com.example.myapplication

import android.R.attr.path
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.validate.ValidatorManager
import com.example.myapplication.validate.validates.StringEmptyValidator
import com.example.myapplication.validate.validates.ZHValidator
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter


class MainActivity : AppCompatActivity() {

    companion object{
        private const val REQUEST_CODE_PERMISSION = 167  //申请权限的REQUEST_CODE
    }

    private lateinit var etName: EditText
    private lateinit var dovalite:Button
    private lateinit var writeReadable:Button
    private lateinit var to_recycle:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     dovalite = findViewById(R.id.dovalite)
        etName = findViewById(R.id.et_name)
        writeReadable = findViewById(R.id.writeReadable)
        to_recycle = findViewById(R.id.to_recycle)

        this.dovalite.setOnClickListener {

            val value = etName?.text?.toString()?.trim()

            var result = ValidatorManager()
                .add(StringEmptyValidator(value,"用户名为空"))
                .add(ZHValidator(value, "必须包含中文！"))
                .doValidate()

            if(!result.isSuccess){
                Toast.makeText(this,result.msg, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "校验通过了",Toast.LENGTH_SHORT).show()
        }

        testPermission()

        writeReadable.setOnClickListener {

            Storage.requestStoragePermission(this, REQUEST_CODE_PERMISSION)

        }
        to_recycle.setOnClickListener {

            val intent = Intent(this,NormalActivity::class.java)

            startActivity(intent)

        }

    }


    private fun testPermission(){
        if(Storage.hasStoragePermission(this)){
            Toast.makeText(this, "有存储权限",Toast.LENGTH_SHORT).show()
        }else {

            Toast.makeText(this, "没有存储权限",Toast.LENGTH_SHORT).show()

            Storage.requestStoragePermission(this, REQUEST_CODE_PERMISSION)
        }

    }

    fun writeToFile(){
        val extStoragePath = Environment.getExternalStorageDirectory()
        val pathDoc = extStoragePath.path + "/test.txt"
        val txt = "我来了"
        try {
            val file2 = File(pathDoc)
            if(!file2.exists()){
                file2.mkdirs()
            }
            val fos = FileOutputStream(pathDoc)
            fos.write(txt.toByteArray())
            fos.close()
            Log.i("hql","path=$path")
            Toast.makeText(this, "写入文件成功：pathDoc：$pathDoc",Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "写入文件失败：pathDoc：$pathDoc",Toast.LENGTH_SHORT).show()
        }
    }

    fun save(){

//        try {
//            val path = Environment.getExternalStorageDirectory()
//            val fileName="smsData.xml"
//            val dirFile= File(path.path+"/xml");
//            if(!dirFile.exists())
//                dirFile.mkdirs()
//
//            val fos = FileOutputStream(File(dirFile,fileName))
//            val writer= OutputStreamWriter(fos,"UTF-8")
//            val w= BufferedWriter(writer)  //缓冲区
//
//            w.write("测试")
//            w.flush()
//            w.close()
//            Log.i("hql","path=${File(dirFile,fileName).path}")
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Toast.makeText(this, "写入文件失败",Toast.LENGTH_SHORT).show()
//        }

        val path = Environment.getExternalStorageDirectory()
        val filePath = (path.absolutePath + File.separator + "xml")
        val filePath2 = (path.absolutePath
                + File.separator
                + "TTGameSDK"
                + File.separator
                + "public"
                + File.separator
                + "db")
        val fileName="smsData.xml"
        val dirFile= File(filePath2)
        if(!dirFile.exists())
            dirFile.mkdirs()

        val fos = FileOutputStream(File(dirFile,fileName))
        val writer= OutputStreamWriter(fos,"UTF-8")
        val w= BufferedWriter(writer)  //缓冲区

        w.write("测试")
        w.flush()
        w.close()
        Log.i("hql","path=${File(dirFile,fileName).path}")
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (Storage.checkPermissionRequest(permissions, grantResults)) {
                //给了权限
                Toast.makeText(this, "获得",Toast.LENGTH_SHORT).show()
                save()
            }else{
                Toast.makeText(this, "没有获得",Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun Appxinxi(packageManager: PackageManager): JSONArray? {
        val array = JSONArray()
        try {
            val packageInfos: List<PackageInfo> =
                packageManager.getInstalledPackages(0)
            for (i in packageInfos.indices) {
                val packageInfo: PackageInfo = packageInfos[i]
                //过滤掉系统app
                if (ApplicationInfo.FLAG_SYSTEM and packageInfo.applicationInfo.flags !== 0) {
                    continue
                }
                val obj = JSONObject()
                obj.put("appid", packageInfo.packageName) //appid
                //appname
                obj.put("appname", packageInfo.applicationInfo.loadLabel(packageManager).toString())
                array.put(obj)
            }
        } catch (e: java.lang.Exception) {
            Log.e("hql", "获取信息失败")
        }
        return array
    }
}
