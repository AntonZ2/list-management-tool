<div class="back-button">
    <button onclick="goBack()">Back</button>
    <script>
        function goBack() {
            var previousPage = '<%= request.getParameter("previousPage") %>';
            if (previousPage) {
                window.location.href = previousPage;
            } else {
                window.history.back();
            }
        }
    </script>
</div>



