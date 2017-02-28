document.write("<p>");
if((campaign.goal - signatureCount)<=0)
{
    document.write(
    <span th:text="${campaign.goal}">1000</span>人の目標を達成しました！！現在
    <span th:text="${signatureCount}">1</span>人の賛同者がいます！ );
}
else
{
    document.write(<span th:text="${campaign.goal}">1000</span>人まで残り<span th:text="${campaign.goal - signatureCount}">1</span>人の賛同者が必要です！);
}
document.write("</p>");