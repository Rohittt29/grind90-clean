import { useEffect, useState } from "react";

function Dashboard() {
  const [message, setMessage] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      const token = localStorage.getItem("token");

      const res = await fetch("https://grind90-clean.onrender.com/test", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      const data = await res.text();

      setMessage(data);
    };

    fetchData();
  }, []);

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>
      <h2>Dashboard 🔐</h2>
      <p>{message}</p>
    </div>
  );
}

export default Dashboard;