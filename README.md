# 🤖 AI Email Writer Backend

A powerful Spring Boot REST API that generates AI-powered email responses using Google's Gemini AI. This backend service provides intelligent email composition capabilities for various communication needs.

## ✨ Features

- **AI-Powered Email Generation**: Leverages Google Gemini AI for intelligent email composition
- **RESTful API**: Clean and simple REST endpoints
- **Cross-Origin Support**: CORS enabled for frontend integration
- **Reactive Programming**: Built with Spring WebFlux for better performance
- **Easy Deployment**: Docker-ready with Render deployment support

## 🛠️ Tech Stack

- **Java 21** - Modern Java runtime
- **Spring Boot 3.5.0** - Framework for building production-ready applications
- **Spring WebFlux** - Reactive web framework
- **Maven** - Dependency management and build tool
- **Lombok** - Reduces boilerplate code
- **Google Gemini AI** - AI service for email generation

## 📋 Prerequisites

- **Java 21** or higher
- **Maven 3.6+** (or use included Maven wrapper)
- **Google Gemini API Key** - Get it from [Google AI Studio](https://makersuite.google.com/app/apikey)

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/ai-email-writer-backend.git
cd ai-email-writer-backend
```

### 2. Set Environment Variables

Create a `.env` file or set the following environment variables:

```bash
export GEMINI_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent
export GEMINI_KEY=your_gemini_api_key_here
```

**Windows (PowerShell):**
```powershell
$env:GEMINI_URL="https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent"
$env:GEMINI_KEY="your_gemini_api_key_here"
```

### 3. Run the Application

**Using Maven Wrapper (Recommended):**
```bash
./mvnw spring-boot:run
```

**Windows:**
```cmd
mvnw.cmd spring-boot:run
```

**Using Installed Maven:**
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8081`

## 📚 API Documentation

### Base URL
```
http://localhost:8081/api/email
```

### Endpoints

#### Generate Email
Generate an AI-powered email response based on the provided request.

**Endpoint:** `POST /api/email/Generate`

**Request Body:**
```json
{
  "subject": "Meeting Request",
  "content": "I would like to schedule a meeting to discuss the project timeline",
  "tone": "professional",
  "type": "request"
}
```

**Response:**
```json
"Dear [Recipient],

I hope this email finds you well. I would like to schedule a meeting with you to discuss the project timeline and ensure we're aligned on our deliverables.

Could we arrange a time that works for both of us sometime this week? I'm flexible with timing and can accommodate your schedule.

Please let me know your availability, and I'll send out a calendar invitation accordingly.

Best regards,
[Your Name]"
```

**cURL Example:**
```bash
curl -X POST http://localhost:8081/api/email/Generate \
  -H "Content-Type: application/json" \
  -d '{
    "subject": "Meeting Request",
    "content": "I would like to schedule a meeting to discuss the project timeline",
    "tone": "professional",
    "type": "request"
  }'
```

## 🐳 Docker Deployment

### Build Docker Image
```bash
docker build -t ai-email-writer-backend .
```

### Run Docker Container
```bash
docker run -p 8081:8081 \
  -e GEMINI_URL="your_gemini_url" \
  -e GEMINI_KEY="your_gemini_key" \
  ai-email-writer-backend
```

## ☁️ Deploy to Render

### 1. Prerequisites
- GitHub account
- Render account (free at [render.com](https://render.com))
- Your code pushed to a GitHub repository

### 2. Deployment Steps

1. **Connect GitHub to Render**:
   - Login to Render
   - Click "New +" → "Web Service"
   - Connect your GitHub repository

2. **Configure Service**:
   - **Name**: `ai-email-writer-backend`
   - **Environment**: `Docker`
   - **Build Command**: `docker build -t app .`
   - **Start Command**: `docker run -p 10000:8081 app`
   - **Instance Type**: Free (or choose paid for production)

3. **Set Environment Variables**:
   ```
   GEMINI_URL=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent
   GEMINI_KEY=your_actual_gemini_api_key
   PORT=8081
   ```

4. **Deploy**: Click "Create Web Service"

### 3. Access Your Deployed API
Your API will be available at: `https://your-service-name.onrender.com`

## 🔧 Configuration

### Application Properties
The application can be configured via `src/main/resources/application.properties`:

```properties
spring.application.name=AIEmail-writer
gemini.api.url=${GEMINI_URL}
gemini.api.key=${GEMINI_KEY}
server.port=8081
```

### Environment Variables

| Variable | Description | Example |
|----------|-------------|---------|
| `GEMINI_URL` | Gemini API endpoint URL | `https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent` |
| `GEMINI_KEY` | Your Gemini API key | `AIzaSyC-your-actual-api-key` |
| `PORT` | Server port (optional) | `8081` |

## 🧪 Testing

### Run Tests
```bash
./mvnw test
```

### Manual Testing
Test the API using tools like:
- **Postman**
- **curl**
- **HTTPie**
- **VS Code REST Client**

Example test request:
```http
POST http://localhost:8081/api/email/Generate
Content-Type: application/json

{
  "subject": "Thank you",
  "content": "Thanks for the great meeting today",
  "tone": "casual",
  "type": "gratitude"
}
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/com/AIEmail/writer/
│   │   ├── AiEmailWriterApplication.java    # Main application class
│   │   ├── EmailController.java             # REST controller
│   │   ├── EmailService.java                # Business logic
│   │   └── EmailReq.java                    # Request DTO
│   └── resources/
│       └── application.properties           # Configuration
├── test/
│   └── java/com/AIEmail/writer/
│       └── AiEmailWriterApplicationTests.java
├── Dockerfile                               # Docker configuration
├── pom.xml                                  # Maven configuration
└── README.md                                # This file
```

## 🚀 Frontend Integration

This backend is designed to work with any frontend framework. Example integration:

### JavaScript/Fetch
```javascript
const generateEmail = async (emailData) => {
  const response = await fetch('https://your-api-url.onrender.com/api/email/Generate', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(emailData)
  });
  
  return await response.text();
};
```

### React Example
```jsx
const [emailResponse, setEmailResponse] = useState('');

const handleGenerateEmail = async () => {
  try {
    const response = await fetch('/api/email/Generate', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        subject: 'Meeting Request',
        content: 'Would like to schedule a meeting',
        tone: 'professional',
        type: 'request'
      })
    });
    
    const generatedEmail = await response.text();
    setEmailResponse(generatedEmail);
  } catch (error) {
    console.error('Error generating email:', error);
  }
};
```

## 🐛 Troubleshooting

### Common Issues

1. **Application won't start**:
   - Verify Java 21 is installed: `java -version`
   - Check environment variables are set
   - Ensure port 8081 is not in use

2. **Gemini API errors**:
   - Verify your API key is correct
   - Check API quotas in Google Cloud Console
   - Ensure internet connectivity

3. **CORS issues**:
   - The API includes `@CrossOrigin(origins = "*")` for development
   - For production, configure specific origins

### Logs
Check application logs for detailed error information:
```bash
./mvnw spring-boot:run --debug
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📞 Support

For support and questions:
- Create an issue in this repository
- Contact: dhruvrajm.rana@gmail.com

## 🔗 Related Projects

- **Frontend Repository**: [Link to your frontend repo]
- **Live Demo**: [Link to deployed application]

---

**Made with ❤️ using Spring Boot and Google Gemini AI by Dhruvraj** 
