$(document).ready(function()
{
    $("#fileuploader").uploadFile({
        url:"/uploadFile",
        fileName:"file",
        showPreview:true,
        previewHeight: "100px",
        previewWidth: "100px",
        onSuccess:function(files,data,xhr,pd)
        {
            console.log(files)
            console.log(data)

            // sauvegarder la photo dans la base de donnée
            $.post("/dashboard/photos/save",{nom : files[0], lien : data[0]}, function (datas) {
                // la photo enregistre dans la base de donnée avec id
                console.log(datas)
                $.post("/dashboard/photos/animal/save",{idAnimal : $("#idAnimal").val(), idPhoto : datas.id}, function (datas2) {
                    // la photo enregistre dans la base de donnée avec id
                    console.log(datas2)
                })
            })
        },
        onLoad:function(obj)
        {
           $.get("/dashboard/photos/animal", {idAnimal: $("#idAnimal").val()}, function (datas) {
               for (let i = 0; i < datas.length; i++) {
                   obj.createProgress(datas[i]["nom"],'/file/display'+datas[i]["lien"],"");
               }
           })
        }
    });
});