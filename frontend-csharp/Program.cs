using System;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Threading.Tasks;

namespace PasteleriaFrontend
{
    class Program
    {
        static async Task Main(string[] args)
        {
            // URL de tu API de Java
            string url = "http://localhost:8080/api/productos";
            
            using (HttpClient client = new HttpClient())
            {
                // Enviamos usuario y contraseña (Basic Auth)
                var authValue = Convert.ToBase64String(Encoding.UTF8.GetBytes("danna:bolillo"));
                client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Basic", authValue);

                Console.WriteLine(" Conectado correctamente de c# a java ");
                
                try {
                    // Hacemos la petición
                    var response = await client.GetStringAsync(url);
                    Console.WriteLine("\n¡Conexion exitosa!");
                    Console.WriteLine("Productos recibidos:");
                    Console.WriteLine("--------------------------------------");
                    Console.WriteLine(response); // aqui salen los pasteles
                    Console.WriteLine("--------------------------------------");
                }
                catch (Exception ex) {
                    Console.WriteLine("\nERROR: ¿Olvidaste de nuevo encender la API de Java?");
                    Console.WriteLine("Mensaje: " + ex.Message);
                }
                Console.WriteLine("\nPresiona cualquier tecla para finalizar...");
                Console.ReadKey();
            }
        }
    }
}