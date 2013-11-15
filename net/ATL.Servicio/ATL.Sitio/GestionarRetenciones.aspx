<%@ Page Title="" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="GestionarRetenciones.aspx.cs" Inherits="ATL.Sitio.GestionarRetenciones" %>
<asp:Content ID="Content1" ContentPlaceHolderID="HeadContent" runat="server">
    <style type="text/css">
        .style1
        {
        }
        .style2
        {
        }
    .style3
    {
        height: 21px;
    }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="MainContent" runat="server">

    <table style="width: 100%;">
        <tr>
            <td class="style1">
                &nbsp;
            <asp:Label ID="Label1" runat="server" 
                    Text="Para Iniciar el Proceso de Levantar Retenciones Presione el siguiente Boton:" 
                    style="font-weight: 700; color: #BB4118"></asp:Label>

                &nbsp;
                &nbsp;
            </td>
        </tr>
        <tr>
            <td class="style2">
                &nbsp;
                &nbsp;
            
    <center>
        <asp:Button ID="btnRetenciones" runat="server" Text="Iniciar Proceso" 
                    style="text-align: center" onclick="btnRetenciones_Click" /></center>
                &nbsp;
            </td>
        </tr>
        <tr>
            <td class="style3">
                &nbsp;
                &nbsp;
               <center> <asp:Label ID="lblIndicador" runat="server" Text=""></asp:Label> </center>
                &nbsp;
            </td>
        </tr>
    </table>
    </asp:Content>
