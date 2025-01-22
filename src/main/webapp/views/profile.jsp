<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile - Java Haven Cafe</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f0f0;
            color: #333;
        }
        .profile-container {
            max-width: 700px;
            margin: 50px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .profile-header {
            text-align: center;
            margin-bottom: 20px;
        }
        .profile-header img {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            border: 5px solid #8a5236;
        }
        .profile-header h1 {
            margin: 10px 0;
            color: #8a5236;
        }
        .profile-info {
            margin: 20px 0;
        }
        .profile-info h2 {
            margin-bottom: 10px;
            color: #8a5236;
        }
        .profile-info p {
            margin: 5px 0;
            font-size: 16px;
            line-height: 1.6;
        }
        .profile-info p strong {
            color: #555;
        }
        .profile-bio {
            margin-top: 20px;
        }
        .profile-bio h2 {
            margin-bottom: 10px;
            color: #8a5236;
        }
        .profile-bio p {
            font-size: 16px;
            line-height: 1.6;
            color: #555;
        }
        .logout-link {
            display: block;
            text-align: center;
            margin: 20px 0;
            font-size: 16px;
            color: #8a5236;
            text-decoration: none;
            border: 2px solid #8a5236;
            padding: 10px;
            border-radius: 5px;
        }
        .logout-link:hover {
            background-color: #8a5236;
            color: #fff;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <div class="profile-header">
            <img src="https://iconscout.com/free-icon/user-1648810" alt="User Profile Picture">
            <h1>Welcome ${modelName}</h1>
        </div>
        <div class="profile-info">
            <h2>${modelName}, You are awesome!!!</h2>
            <p><strong>Email:</strong> ${modelEmail}</p>
            <p><strong>Phone:</strong> ${modelPhone}</p>
            <p><strong>Address:</strong> ${modelAddress}</p>
        </div>
        <div class="profile-bio">
            <h2>Bio</h2>
            <p>Welcome to my profile! I'm ${modelName}, a coffee enthusiast who loves exploring new cafes and trying out different brews. At Cafe Dine, I enjoy the cozy ambiance, the rich flavors of their coffee, and the warm community. When I'm not savoring a cup of espresso, you can find me reading a good book or experimenting with new recipes in the kitchen. Looking forward to connecting with fellow coffee lovers!</p>
        </div>
        <a href="logout" class="logout-link">Logout</a>
    </div>
</body>
</html>
