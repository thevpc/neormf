//var : $$NAME$$
var EditableChoiceTypeHtmlSupportScript_Old$$NAME$$ = "";
var EditableChoiceTypeHtmlSupportScript_ListText$$NAME$$ = new Array;
var EditableChoiceTypeHtmlSupportScript_ListValue$$NAME$$ = new Array;

function EditableChoiceTypeHtmlSupportScript_UdpateList$$NAME$$(l,txt) {
   var nb;
   if (txt != EditableChoiceTypeHtmlSupportScript_Old$$NAME$$) {
      EditableChoiceTypeHtmlSupportScript_Old$$NAME$$ = txt;
      nb = EditableChoiceTypeHtmlSupportScript_ListText$$NAME$$.length;
      l.options.length=0;
      for (var i=0; i<nb; i++) {
          if ( EditableChoiceTypeHtmlSupportScript_ListText$$NAME$$[i].substring(0,txt.length).toUpperCase()==txt.toUpperCase() ) {
             var o=new Option(EditableChoiceTypeHtmlSupportScript_ListText$$NAME$$[i], EditableChoiceTypeHtmlSupportScript_ListValue$$NAME$$[i]);
             l.options[l.options.length]=o;
          }
      }
      if (l.options.length==1) {
         l.selectedIndex=0;
      }
   }
}

function EditableChoiceTypeHtmlSupportScript_CheckList$$NAME$$() {
  EditableChoiceTypeHtmlSupportScript_UdpateList$$NAME$$(document.forms["form1"].elements["$$NAME$$"], document.forms["form1"].elements["$$NAME$$_InputTextFilter"].value);
  setTimeout("EditableChoiceTypeHtmlSupportScript_CheckList$$NAME$$()", 1001);
}

function EditableChoiceTypeHtmlSupportScript_InitList$$NAME$$() {
    var liste=document.forms["form1"].elements["$$NAME$$"];
     var l = liste.options.length;
     for (var i = 0; i<l; i++) {
       EditableChoiceTypeHtmlSupportScript_ListText$$NAME$$[i]=liste.options[i].text;
       EditableChoiceTypeHtmlSupportScript_ListValue$$NAME$$[i]=liste.options[i].value;
     }
}
EditableChoiceTypeHtmlSupportScript_InitList$$NAME$$();
EditableChoiceTypeHtmlSupportScript_CheckList$$NAME$$();
