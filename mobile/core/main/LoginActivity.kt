class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)
        val login = findViewById<Button>(R.id.btnLogin)
        val welcome = findViewById<TextView>(R.id.tvWelcome)

        login.setOnClickListener {
            if (username.text.toString() == "admin" && password.text.toString() == "admin") {
                welcome.visibility = View.VISIBLE
            }
        }
    }
}
